package com.elsy.simpleinstagram.app.Home;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.elsy.simpleinstagram.utils.camera.CameraHelper;
import com.elsy.simpleinstagram.utils.camera.PermissionsHelper;

import java.io.IOException;

public class HomePresenter implements HomeContract.UserActionsListener {

    private Activity activity;
    private HomeContract.HomeView view;
    private CameraHelper camera;

    private String ERROR_ADD_NEW_POST = "This permission is required to share a new post";
    private String ERROR_NOT_CAMERAA = "This device don't have a camera";

    public HomePresenter(HomeContract.HomeView view, CameraHelper camera) {
        this.view = view;
        this.activity = ((AppCompatActivity) view);
        this.camera = camera;
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
        extras.putString("filepath",  camera.getPictureFilePath());
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
            view.showFailedLoadMessage(ERROR_NOT_CAMERAA);
        }
    }
}
