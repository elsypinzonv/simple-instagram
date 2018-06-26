package com.elsy.simpleinstagram.app.dispatch;

class DispatchContract {

    interface DispatchView {

        void onError(String error);

        void sendToHome();

    }

    interface UserActionsListener {

        void begin();

    }
}
