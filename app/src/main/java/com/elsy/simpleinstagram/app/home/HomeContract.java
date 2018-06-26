package com.elsy.simpleinstagram.app.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.elsy.simpleinstagram.domain.Post;

import java.util.List;

class HomeContract {

    interface HomeView {

        void sendToNewPost(Bundle extras);

        void sendPostDetail(Bundle extras);

        void showFailedLoadMessage(String error);

        void refreshRecyclerView(List<Post> list);

    }

    interface UserActionsListener {

        void onRequestPermissionResult(int requestCode, @NonNull int[] grantResults);

        void onClickPicture(Post post, View pictureView);

        void shareNewPost();

        void takePicture();

        void begin();

    }
}
