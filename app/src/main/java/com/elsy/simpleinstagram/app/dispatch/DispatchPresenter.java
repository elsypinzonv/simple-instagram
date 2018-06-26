package com.elsy.simpleinstagram.app.dispatch;

import com.elsy.simpleinstagram.data.common.Repository;
import com.elsy.simpleinstagram.data.remote.callbacks.ListCallback;
import com.elsy.simpleinstagram.domain.Post;

import java.util.List;

public class DispatchPresenter implements DispatchContract.UserActionsListener, ListCallback<Post> {

    private DispatchContract.DispatchView view;
    private Repository<Post> postRepository;

    DispatchPresenter(DispatchContract.DispatchView view, Repository<Post> postRepository) {
        this.view = view;
        this.postRepository = postRepository;
    }

    @Override
    public void begin() {
        postRepository.getAll(this);
    }

    @Override
    public void onItemsLoaded(List<Post> posts) {
        view.sendToHome();
    }

    @Override
    public void onNetworkError(String networkErrorMessage) {
        view.onError(networkErrorMessage);
    }

    @Override
    public void onServerError(String serverErrorMessage) {
        view.onError(serverErrorMessage);
    }
}
