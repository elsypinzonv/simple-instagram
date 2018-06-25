package com.elsy.simpleinstagram.data.remote.callbacks;

public interface ServerCallback {

    void onNetworkError(String networkErrorMessage);

    void onServerError(String serverErrorMessage);

}
