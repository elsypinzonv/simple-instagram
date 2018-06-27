package com.elsy.simpleinstagram.data.remote;

import com.elsy.simpleinstagram.data.remote.callback.ListCallback;
import com.elsy.simpleinstagram.interactor.PostInteractor;
import com.elsy.simpleinstagram.data.common.Repository;
import com.elsy.simpleinstagram.domain.Post;

import static com.elsy.simpleinstagram.util.ActivityHelper.checkNotNull;

public class PostRemoteRepository implements Repository<Post> {

    private final PostInteractor interactor;

    public PostRemoteRepository(
            PostInteractor interactor
    ){
        this.interactor = checkNotNull(interactor);
    }

    /**
     * Fetch all post that belongs to the current application user.
     * @param callback observer of the server request.
     */
    @Override
    public void getAll(ListCallback<Post> callback) {
        interactor.getPosts(callback);
    }

}
