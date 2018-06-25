package com.elsy.simpleinstagram.data.common;

import com.elsy.simpleinstagram.data.remote.callbacks.ListCallback;

/**
 * Main entry point for accessing domain data across the application.
 * This is the main interface for accessing the data layer.
 * @param <T>
 */
public interface Repository<T> {

    void add(T item);

    void getAll(ListCallback<T> callback);

}
