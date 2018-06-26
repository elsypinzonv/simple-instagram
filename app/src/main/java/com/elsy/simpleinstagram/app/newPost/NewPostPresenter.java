package com.elsy.simpleinstagram.app.newPost;

import android.app.Activity;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import com.elsy.simpleinstagram.data.common.PostsRepository;
import com.elsy.simpleinstagram.data.local.realm.RealmCallback;
import com.elsy.simpleinstagram.domain.Post;
import com.elsy.simpleinstagram.utils.ActivityHelper;
import com.elsy.simpleinstagram.utils.AppConstants;

import java.io.File;

public class NewPostPresenter implements NewPostContract.UserActionsListener, RealmCallback {


    private Activity activity;
    private NewPostContract.NewPostView view;
    private PostsRepository repository;
    private String filePath;
    private static final String ERROR_EMPTY = "This field is obligatory";
    private static final String ERROR_PICTURE_NOT_FOUND = "This field is obligatory";
    private static final String ID_FORMAT = "yyyyMMddHHmmss";
    private static final String PUBLISHED_AT_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    NewPostPresenter(NewPostContract.NewPostView view, PostsRepository repository) {
        this.view = view;
        this.activity = ((AppCompatActivity) view);
        this.repository = repository;
    }


    @Override
    public void getPicture() {
        String path = activity.getIntent().getStringExtra(AppConstants.KEY_POST);
        this.filePath = path;
        if (path != null) {
            Uri imageUri = Uri.parse(path);
            File imgFile = new File(imageUri.getPath());
            if (imgFile.exists()) {
                view.showPicture(imgFile);
                return;
            }
        }
        view.showFailedLoadMessage(ERROR_PICTURE_NOT_FOUND);
    }

    @Override
    public void savePost(String title, String comment) {
        if (!title.isEmpty() && !comment.isEmpty()) {
            Post post = new Post();
            post.setId(getID());
            post.setPhoto(filePath);
            post.setTitle(title);
            post.setComment(comment);
            post.setPublishedAt(getPublishedAt());
            repository.add(post, this);
        } else view.showValidationErrors(ERROR_EMPTY);
    }

    @Override
    public void onSuccessRealmAction() {
        view.sendToHome();
    }

    @Override
    public void onErrorRealmAction(String serverErrorMessage) {
        view.showFailedLoadMessage(serverErrorMessage);
    }

    private String getID() {
        return ActivityHelper.getTimeString(ID_FORMAT);
    }

    private String getPublishedAt() {
        return ActivityHelper.getTimeString(PUBLISHED_AT_FORMAT);
    }

}