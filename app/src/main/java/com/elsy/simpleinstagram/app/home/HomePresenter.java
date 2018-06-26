package com.elsy.simpleinstagram.app.home;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.elsy.simpleinstagram.data.common.Repository;
import com.elsy.simpleinstagram.data.remote.callbacks.ListCallback;
import com.elsy.simpleinstagram.domain.Post;
import com.elsy.simpleinstagram.utils.AppConstants;
import com.elsy.simpleinstagram.utils.camera.CameraHelper;
import com.elsy.simpleinstagram.utils.camera.PermissionsHelper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

public class HomePresenter implements HomeContract.UserActionsListener, ListCallback<Post> {

    private final Activity activity;
    private final HomeContract.HomeView view;
    private final CameraHelper camera;
    private final Repository<Post> postRepository;

    private static final String ERROR_ADD_NEW_POST = "This permission is required to share a new post";
    private static final String ERROR_NOT_CAMERA = "This device don't have a camera";
    private final Gson gson;

    HomePresenter(
            HomeContract.HomeView view,
            Gson gson, CameraHelper camera,
            Repository<Post> postRepository
    ) {
        this.view = view;
        this.gson = gson;
        this.activity = ((AppCompatActivity) view);
        this.camera = camera;
        this.postRepository = postRepository;
    }

    @Override
    public void onRequestPermissionResult(int requestCode, @NonNull int[] grantResults) {
        switch(requestCode) {
            case PermissionsHelper.CAMERA_CODE:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED)
                    view.showFailedLoadMessage(ERROR_ADD_NEW_POST);
                    break;
            case PermissionsHelper.WRITE_EXTERNAL_STORAGE_CODE:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED)
                    view.showFailedLoadMessage(ERROR_ADD_NEW_POST);
                    break;
        }
    }

    @Override
    public void onClickPicture(Post post, View pictureView) {
        Bundle extras = ActivityOptionsCompat
                .makeScaleUpAnimation(pictureView, 0, 0, pictureView.getWidth(), pictureView.getHeight())
                .toBundle();
        extras.putSerializable(AppConstants.KEY_POST, gson.toJson(post));
        view.sendPostDetail(extras);
    }

    @Override
    public void shareNewPost() {
        Bundle extras = new Bundle();
        extras.putString(AppConstants.KEY_FILE_PATH,  camera.getPictureFilePath());
        view.sendToNewPost(extras);
    }

    @Override
    public void takePicture() {
        if(activity.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
            try {
                camera.execute();
            } catch (IOException e) {
                view.showFailedLoadMessage(e.getMessage());
            }
        } else {
            view.showFailedLoadMessage(ERROR_NOT_CAMERA);
        }
    }

    @Override
    public void begin() {
        postRepository.getAll(this);
    }

    @Override
    public void onItemsLoaded(List<Post> posts) {
        view.refreshRecyclerView(posts);
    }

    @Override
    public void onNetworkError(String networkErrorMessage) {
        view.showFailedLoadMessage(networkErrorMessage);
    }

    @Override
    public void onServerError(String serverErrorMessage) {
        view.showFailedLoadMessage(serverErrorMessage);
    }

}
