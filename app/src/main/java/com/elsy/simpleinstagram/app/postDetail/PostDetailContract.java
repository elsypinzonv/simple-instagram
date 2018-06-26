package com.elsy.simpleinstagram.app.postDetail;

import com.elsy.simpleinstagram.domain.Post;

import java.io.File;

public interface PostDetailContract {

    interface PostDetailView {

        void showPost(Post post);

        void showFailedLoadMessage(String error);

        void setPicture(File imageFile);

        void setPicture(String url);

    }

    interface UserActionsListener {

        void loadPost();

        void loadImage(String image);

    }
}
