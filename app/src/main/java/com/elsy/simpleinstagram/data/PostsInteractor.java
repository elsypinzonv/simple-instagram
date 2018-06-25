package com.elsy.simpleinstagram.data;

import android.util.Log;

import com.elsy.simpleinstagram.data.remote.callbacks.ListCallback;
import com.elsy.simpleinstagram.data.remote.service.PostsService;
import com.elsy.simpleinstagram.domain.Post;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsInteractor extends BaseInteractor<PostsService> {

    public PostsInteractor(PostsService sessionsAPIService) {
        super(sessionsAPIService);
    }

    public void getPosts(final ListCallback<Post> callback){
        Call<List<Post>> call = apiService.listPosts();
        Log.d("TESTK", "REQUEST: " + call.request().toString());

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                Log.d("TESTK", "RESPONSE: " + response.raw().toString());

                if (response.isSuccessful()) {
                    List<Post> responseBody = response.body();
                    callback.onItemsLoaded(responseBody);
                } else {
                    callback.onServerError("Ha ocurrido un error");
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                t.printStackTrace();
                callback.onServerError(t.getMessage());
            }
        });
    }
}
