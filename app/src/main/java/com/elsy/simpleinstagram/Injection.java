package com.elsy.simpleinstagram;

import android.app.Activity;

import com.elsy.simpleinstagram.utils.camera.CameraHelper;
import com.google.gson.Gson;

public class Injection {

    public static Gson provideGSON() {
        return new Gson();
    }


    public static CameraHelper provideCameraHelper(Activity activity) {
        return new CameraHelper(activity);
    }


}
