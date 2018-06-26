package com.elsy.simpleinstagram.data.remote.service;

import com.elsy.simpleinstagram.data.APIConstants;
import com.elsy.simpleinstagram.domain.Post;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface PostsService {

    @GET(APIConstants.POSTS_COLLECTION_ENDPOINT)
    Call<List<Post>> listPosts();
}
