package com.elsy.simpleinstagram.data.remote.callback;

public interface ServerCallback {

    void onNetworkError(String networkErrorMessage);

    void onServerError(String serverErrorMessage);

}
