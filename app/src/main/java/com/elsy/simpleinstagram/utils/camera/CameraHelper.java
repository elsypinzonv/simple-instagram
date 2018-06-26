package com.elsy.simpleinstagram.utils.camera;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraHelper {


    private Activity activity;
    private String pictureFilePath;
    public static final int REQUEST_PICTURE_CAPTURE = 1;

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
                getAutorities(),
                pictureFile
        );
    }

    private String getAutorities(){
        return activity.getApplicationContext().getPackageName() + ".fileprovider";
    }

    private File getPictureFile() throws IOException {
        File image = File.createTempFile(getTimeStamp(),  ".jpg", getStorageDir());
        this.pictureFilePath = image.getAbsolutePath();
        return image;
    }


    private String getTimeStamp() {
        return new SimpleDateFormat(
                "yyyyMMddHHmmss"
        ).format(new Date());
    }

    private File getStorageDir() {
        return activity.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    }

}
