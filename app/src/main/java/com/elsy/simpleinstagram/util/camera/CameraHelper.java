package com.elsy.simpleinstagram.util.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;

import com.elsy.simpleinstagram.util.ActivityHelper;

import java.io.File;
import java.io.IOException;

public class CameraHelper {

    public static final int REQUEST_PICTURE_CAPTURE = 1;
    private static final String FILE_PROVIDER_PATH = ".fileprovider";
    private static final String SUFFIX_IMAGE_FORMAT = ".jpg";
    private static final String TIME_FORMAT = "yyyyMMddHHmmss";
    private final Activity activity;
    private String pictureFilePath;

    public CameraHelper(Activity activity) {
        this.activity = activity;
    }

    public void execute() throws IOException {
        if(PermissionsHelper.check(Manifest.permission.CAMERA, PermissionsHelper.CAMERA_CODE, activity) &&
                PermissionsHelper.check(Manifest.permission.WRITE_EXTERNAL_STORAGE, PermissionsHelper.WRITE_EXTERNAL_STORAGE_CODE, activity) ){

            Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraIntent.putExtra( MediaStore.EXTRA_FINISH_ON_COMPLETION, true);

            if (cameraIntent.resolveActivity(activity.getPackageManager()) != null) {
                File pictureFile = getPictureFile();
                if (pictureFile != null) {
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, getUri(pictureFile));
                    activity.startActivityForResult(cameraIntent, REQUEST_PICTURE_CAPTURE);
                }
            }
        }
    }

    public String getPictureFilePath() {
        return pictureFilePath;
    }

    private Uri getUri(File pictureFile){
        return FileProvider.getUriForFile(
                activity,
                getAuthorities(),
                pictureFile
        );
    }

    private String getAuthorities(){
        return activity.getApplicationContext().getPackageName() + FILE_PROVIDER_PATH;
    }

    private File getPictureFile() throws IOException {
        File image = File.createTempFile(getTimeStamp(),  SUFFIX_IMAGE_FORMAT, getStorageDir());
        this.pictureFilePath = image.getAbsolutePath();
        return image;
    }


    private String getTimeStamp() {
        return ActivityHelper.getTimeString(TIME_FORMAT);
    }

    private File getStorageDir() {
        return activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    }
}
