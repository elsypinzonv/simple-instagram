package com.elsy.simpleinstagram.data.remote;

import com.elsy.simpleinstagram.data.remote.callbacks.ListCallback;
import com.elsy.simpleinstagram.data.PostInteractor;
import com.elsy.simpleinstagram.data.common.Repository;
import com.elsy.simpleinstagram.domain.Post;

import static com.elsy.simpleinstagram.utils.ActivityHelper.checkNotNull;

public class PostRemoteRepository implements Repository<Post> {

    private PostInteractor interactor;

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
