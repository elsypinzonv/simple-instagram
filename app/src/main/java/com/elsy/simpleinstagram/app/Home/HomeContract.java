package com.elsy.simpleinstagram.app.Home;

import android.os.Bundle;
import android.support.annotation.NonNull;

public class HomeContract {

    interface HomeView {

        void sendToNewPost(Bundle extras);

        void sendToPostDetail();

        void showFailedLoadMessage(String error);

    }

    interface UserActionsListener {

        void onRequestPermissionResult(int requestCode, @NonNull int[] grantResults);

        void shareNewPost();

        void takePicture();

    }
}
