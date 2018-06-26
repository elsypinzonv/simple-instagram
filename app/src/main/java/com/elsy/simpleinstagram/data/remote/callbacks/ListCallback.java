package com.elsy.simpleinstagram.data.remote.callbacks;

import java.util.List;

public interface ListCallback<Item> extends ServerCallback {
    void onItemsLoaded(List<Item> items);
}
