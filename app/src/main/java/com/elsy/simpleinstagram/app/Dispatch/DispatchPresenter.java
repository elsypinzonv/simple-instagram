package com.elsy.simpleinstagram.app.Dispatch;

public class DispatchPresenter implements DispatchContract.UserActionsListener {

    private DispatchContract.DispatchView view;

    public DispatchPresenter(DispatchContract.DispatchView view) {
        this.view = view;
    }

    @Override
    public void doDispatch() {
        view.sendHome();
    }
}
