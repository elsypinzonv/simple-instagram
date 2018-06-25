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
import java.util.Map;

import static com.elsy.simpleinstagram.utils.ActivityHelper.checkNotNull;

public class PostsRepository implements Repository<Post> {

    private static PostsRepository INSTANCE = null;

    private final PostRemoteRepository mRemoteDataSource;

    private final PostLocalRepository mLocalDataSource;

    /**
     * This variable has package local visibility so it can be accessed from tests.
     */
    Map<String, Post> mCache;

    /**
     * Marks the cache as invalid, to force an update the next time data is requested. This variable
     * has package local visibility so it can be accessed from tests.
     */
    boolean mCacheIsDirty = false;

    private PostsRepository(
            @NonNull PostRemoteRepository remoteDataSource,
            @NonNull PostLocalRepository localDataSource) {
        this.mRemoteDataSource = checkNotNull(remoteDataSource);
        this.mLocalDataSource = checkNotNull(localDataSource);
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
        if (mCache != null && !mCacheIsDirty) {
            callback.onItemsLoaded(new ArrayList<>(mCache.values()));
            return;
        }

        if (mCacheIsDirty) {
            // If the cache is dirty we need to fetch new data from the network.
            getFromRemoteDataSource(callback);
        } else {
            // Query the local storage if available. If not, query the network.
            mLocalDataSource.getAll(new ListCallback<Post>() {
                @Override
                public void onItemsLoaded(List<Post> list) {
                    refreshCache(list);
                    callback.onItemsLoaded(new ArrayList<>(mCache.values()));
                }

                @Override
                public void onNetworkError(String networkErrorMessage) {
                    //Do nothing
                }

                @Override
                public void onServerError(String serverErrorMessage) {
                    getFromRemoteDataSource(callback);
                }

            });
        }
    }

    /**
     * Sets the cached state to dirty in order to directly call server on any request.
     */
    public void refresh() {
        mCacheIsDirty = true;
    }

    public void add(Post post, RealmCallback callback) {
        checkNotNull(post);
        mLocalDataSource.add(post, callback);

        if (mCache == null) {
            mCache = new LinkedHashMap<>();
        }
        mCache.put(post.getId(), post);
    }


    /**
     * Makes remote call using remote data source implementation.
     *
     * @param callback delegate observer of request.
     */
    private void getFromRemoteDataSource(final ListCallback<Post> callback) {
        mRemoteDataSource.getAll(new ListCallback<Post>() {
            @Override
            public void onItemsLoaded(List<Post> list) {
                refreshCache(list);
                callback.onItemsLoaded(new ArrayList<>(mCache.values()));
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
     * Updates cache content.
     * @param posts updated list of objects.
     */
    private void refreshCache(List<Post> posts) {
        if (mCache == null) {
            mCache = new LinkedHashMap<>();
        }
        mCache.clear();
        for (Post post : posts) {
            mCache.put(post.getId(), post);
        }
        mCacheIsDirty = false;
    }

    /**
     * Adds cache content.
     * @param posts list of new objects.
     */
    private void addToLocalDataSource(List<Post> posts) {
        for (Post post : posts) {
            mCache.put(post.getId(), post);
        }
    }

}
