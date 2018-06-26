package com.elsy.simpleinstagram.app.postDetail;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import com.elsy.simpleinstagram.domain.Post;
import com.google.gson.Gson;

import java.io.File;

import static com.elsy.simpleinstagram.utils.ActivityHelper.checkNotNull;

public class PostDetailPresenter implements PostDetailContract.UserActionsListener {

    private Gson gson;
    private Activity activityContext;
    protected PostDetailContract.PostDetailView view;
    private final String KEY_POST = "post";
    private final String ERROR_MESSAGE = "Error getting the data";

    public PostDetailPresenter (
            PostDetailContract.PostDetailView view,
            Gson gson
    ) {
        this.view = view;
        this.activityContext = ((AppCompatActivity) view);
        this.gson = gson;
    }

    @Override
    public void loadPost() {
        String postStr = activityContext.getIntent().getStringExtra(KEY_POST);
        Post post = gson.fromJson(postStr, Post.class);
        try {
            checkNotNull(post);
            view.showPost(post);
        } catch (NullPointerException e) {
            view.showFailedLoadMessage(ERROR_MESSAGE);
        }
    }

    @Override
    public void loadImage(String image) {
        if(image != null){
            Uri imageUri = Uri.parse(image);
            File imgFile = new File(imageUri.getPath());
            if(imgFile.exists()) {
                view.setPicture(imgFile);
            } else{
                view.setPicture(image);
            }
        }
    }


}
