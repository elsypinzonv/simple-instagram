package com.elsy.simpleinstagram.data.common;

import android.support.annotation.NonNull;

import com.elsy.simpleinstagram.data.local.PostLocalRepository;
import com.elsy.simpleinstagram.data.local.realm.RealmCallback;
import com.elsy.simpleinstagram.data.remote.PostRemoteRepository;
import com.elsy.simpleinstagram.data.remote.callbacks.ListCallback;
import com.elsy.simpleinstagram.domain.Post;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.elsy.simpleinstagram.utils.ActivityHelper.checkNotNull;

public class PostsRepository implements Repository<Post> {

    private static PostsRepository INSTANCE = null;

    private final PostRemoteRepository remoteDataSource;

    private final PostLocalRepository localDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    ArrayList<Post> cache;


    private PostsRepository(
            @NonNull PostRemoteRepository remoteDataSource,
            @NonNull PostLocalRepository localDataSource) {
        this.remoteDataSource = checkNotNull(remoteDataSource);
        this.localDataSource = checkNotNull(localDataSource);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param remoteDataSource the backend data source
     * @param localDataSource  the device storage data source
     * @return the {@link Repository} instance
     */
    public static PostsRepository getInstance(PostRemoteRepository remoteDataSource,
                                              PostLocalRepository localDataSource){
        if (INSTANCE == null) {
            INSTANCE = new PostsRepository(remoteDataSource, localDataSource);
        }
        return INSTANCE;
    }

    /**
     * Used to force {@link #getInstance(PostRemoteRepository, PostLocalRepository)} to create a new instance
     * next time it's called.
     */
    public static void destroyInstance() {
        INSTANCE = null;
    }

    /**
     * Gets posts from cache, local data source (Realm) or remote data source, whichever is
     * available first.
     * <p>
     * Method {@link com.elsy.simpleinstagram.data.remote.callbacks.ServerCallback#onServerError(String)}
     * is fired if all data sources fail to get the data.
     *
     * @param callback observer of the server request that expects a list of results.
     */
    @Override
    public void getAll(final ListCallback<Post> callback) {
        checkNotNull(callback);

        // Respond immediately with cache if available and not dirty
        if (cache != null) {
            callback.onItemsLoaded(cache);
            return;
        }

        localDataSource.getAll(new ListCallback<Post>() {
            @Override
            public void onItemsLoaded(List<Post> list) {
                addToCache(list);
                callback.onItemsLoaded(cache);
                getFromRemoteDataSource(callback);
            }

            @Override
            public void onNetworkError(String networkErrorMessage) {
                getFromRemoteDataSource(callback);
            }

            @Override
            public void onServerError(String serverErrorMessage) {
                getFromRemoteDataSource(callback);
            }

        });

    }

    public void add(Post post, RealmCallback callback) {
        checkNotNull(post);
        localDataSource.add(post, callback);
        if (cache == null) {
            cache = new ArrayList<>();
        }
        cache.add(0,post);
    }


    /**
     * Makes remote call using remote data source implementation.
     *
     * @param callback delegate observer of request.
     */
    private void getFromRemoteDataSource(final ListCallback<Post> callback) {
        remoteDataSource.getAll(new ListCallback<Post>() {
            @Override
            public void onItemsLoaded(List<Post> list) {
                addToCache(list);
                callback.onItemsLoaded(cache);
            }

            @Override
            public void onNetworkError(String networkErrorMessage) {
                callback.onNetworkError(networkErrorMessage);
            }

            @Override
            public void onServerError(String serverErrorMessage) {
                callback.onServerError(serverErrorMessage);
            }

        });
    }

    /**
     * Adds cache content.
     * @param posts list of new objects.
     */
    private void addToCache(List<Post> posts) {
        if (cache == null) {
            cache = new ArrayList<>();
        }
        for (Post post : posts) {
            cache.add(post);
        }
    }

}