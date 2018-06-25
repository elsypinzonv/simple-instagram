package com.elsy.simpleinstagram.app.NewPost;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

public class NewPostPresenter implements NewPostContract.UserActionsListener{


    private Activity activity;
    private NewPostContract.NewPostView view;

    public NewPostPresenter(NewPostContract.NewPostView view) {
        this.view = view;
        this.activity = ((AppCompatActivity) view);
    }


    @Override
    public void getPicture() {
        String path =  activity.getIntent().getStringExtra("filepath");
        Uri imageUri = Uri.parse(path);
        File imgFile = new File(imageUri.getPath());
        if(imgFile.exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);
            int imageHeight = options.outHeight;
            int imageWidth = options.outWidth;
            if (imageHeight > 4096 || imageWidth > 4096) {
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = 4;
                Bitmap bitmap = BitmapFactory.decodeFile(imageUri.toString(), opts);
                view.showPicture(bitmap);
            } else {
                view.showPicture(imgFile);
            }
        }
        view.showFailedLoadMessage("Error getting the picture");
    }

    @Override
    public void savePost() {

    }
}
