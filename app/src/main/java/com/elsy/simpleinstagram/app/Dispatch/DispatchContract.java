package com.elsy.simpleinstagram.app.Dispatch;

public class DispatchContract {

    interface DispatchView {

        void sendHome();

    }

    interface UserActionsListener {

        void doDispatch();

    }
}
