package com.elsy.simpleinstagram.app.PostDetail;

import com.elsy.simpleinstagram.domain.Post;

public interface PostDetailContract {

    interface PostDetailView {

        void showPost(Post post);

        void showFailedLoadMessage(String error);

    }

    interface UserActionsListener {

        void loadPost();

    }
}
