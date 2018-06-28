package com.elsy.simpleinstagram.interactor;

import android.support.annotation.NonNull;

import com.elsy.simpleinstagram.data.remote.callback.ListCallback;
import com.elsy.simpleinstagram.data.remote.service.PostsService;
import com.elsy.simpleinstagram.domain.Post;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostInteractor extends BaseInteractor<PostsService> {

    private final String  SERVER_ERROR ="An error has occurred in the server";

    public PostInteractor(PostsService sessionsAPIService) {
        super(sessionsAPIService);
    }

    public void getPosts(final ListCallback<Post> callback){
        Call<List<Post>> call = apiService.listPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(@NonNull Call<List<Post>> call, @NonNull Response<List<Post>> response) {

                if (response.isSuccessful()) {
                    List<Post> responseBody = response.body();
                    callback.onItemsLoaded(responseBody);
                } else {
                    callback.onServerError(SERVER_ERROR);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Post>> call, @NonNull Throwable t) {
                t.printStackTrace();
                callback.onServerError(t.getMessage());
            }
        });
    }
}
