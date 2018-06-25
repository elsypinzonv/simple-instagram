package com.elsy.simpleinstagram.data.local.realm;

public interface RealmCallback {

    void onSuccessRealmAction();

    void onErrorRealmAction(String serverErrorMessage);
}
