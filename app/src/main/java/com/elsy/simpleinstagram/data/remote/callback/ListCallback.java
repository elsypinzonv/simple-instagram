package com.elsy.simpleinstagram.data.remote.callback;

import java.util.List;

public interface ListCallback<Item> extends ServerCallback {
    void onItemsLoaded(List<Item> items);
}
