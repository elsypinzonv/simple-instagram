package com.elsy.simpleinstagram.app.newPost;

import java.io.File;


public interface NewPostContract {

    interface NewPostView {

        void showPicture(File file);

        void sendToHome();

        void showFailedLoadMessage(String error);

        void showValidationErrors(String error);
    }

    interface UserActionsListener {

        void getPicture();

        void savePost(String title, String comment);

    }
}