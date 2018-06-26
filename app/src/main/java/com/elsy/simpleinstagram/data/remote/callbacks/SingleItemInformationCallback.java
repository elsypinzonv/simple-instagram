package com.elsy.simpleinstagram.data.remote.callbacks;

import com.elsy.simpleinstagram.data.remote.callbacks.ServerCallback;

public interface SingleItemInformationCallback<Item> extends ServerCallback {

    void onItemInformationLoaded(Item item);

}