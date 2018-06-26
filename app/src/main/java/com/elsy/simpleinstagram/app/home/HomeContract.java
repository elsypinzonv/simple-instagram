package com.elsy.simpleinstagram.app.home;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.elsy.simpleinstagram.domain.Post;

import java.util.List;

public class HomeContract {

    interface HomeView {

        void sendToNewPost(Bundle extras);


        void showFailedLoadMessage(String error);

        void refreshRecyclerView(List<Post> list);

    }

    interface UserActionsListener {

        void onRequestPermissionResult(int requestCode, @NonNull int[] grantResults);

        void shareNewPost();

        void takePicture();

        void begin();

    }
}
