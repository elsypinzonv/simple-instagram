package com.elsy.simpleinstagram.app.NewPost;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.elsy.simpleinstagram.domain.Post;

import java.io.File;


public interface NewPostContract {

    interface NewPostView {

        void showPicture(Bitmap bitmap);

        void showPicture(File file);

        void showFailedLoadMessage(String error);

    }

    interface UserActionsListener {

        void getPicture();

        void savePost();

    }
}