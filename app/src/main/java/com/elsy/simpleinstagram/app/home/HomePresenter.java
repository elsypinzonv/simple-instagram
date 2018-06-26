package com.elsy.simpleinstagram.app.home;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.elsy.simpleinstagram.data.common.Repository;
import com.elsy.simpleinstagram.data.remote.callbacks.ListCallback;
import com.elsy.simpleinstagram.domain.Post;
import com.elsy.simpleinstagram.utils.AppConstants;
import com.elsy.simpleinstagram.utils.camera.CameraHelper;
import com.elsy.simpleinstagram.utils.camera.PermissionsHelper;

import java.io.IOException;
import java.util.List;

public class HomePresenter implements HomeContract.UserActionsListener, ListCallback<Post> {

    private Activity activity;
    private HomeContract.HomeView view;
    private CameraHelper camera;
    private Repository<Post> postRepository;

    private static final String ERROR_ADD_NEW_POST = "This permission is required to share a new post";
    private static final String ERROR_NOT_CAMERA = "This device don't have a camera";

    HomePresenter(HomeContract.HomeView view, CameraHelper camera, Repository<Post> postRepository) {
        this.view = view;
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
