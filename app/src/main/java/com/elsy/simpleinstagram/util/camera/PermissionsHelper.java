package com.elsy.simpleinstagram.util.camera;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class PermissionsHelper {

    public static final int CAMERA_CODE = 100;
    public static final int WRITE_EXTERNAL_STORAGE_CODE = 200;

    public static boolean check(String permission, int code, Activity activity){
        if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
            requestPermission(permission, code, activity);
            return false;
        }
        return true;
    }

    private static void requestPermission(String permission, int code, Activity activity) {
            ActivityCompat.requestPermissions(activity, new String[]{permission}, code);
    }
}
