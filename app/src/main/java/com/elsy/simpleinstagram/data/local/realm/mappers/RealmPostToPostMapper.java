package com.elsy.simpleinstagram.data.local.realm.mappers;

import com.elsy.simpleinstagram.data.local.realm.models.RealmPost;
import com.elsy.simpleinstagram.domain.Post;

public class RealmPostToPostMapper implements Mapper<RealmPost, Post> {

    @Override
    public Post map(RealmPost realmPost) {
        Post post = new Post();

        post.setId(realmPost.getId());
        post.setTitle(realmPost.getTitle());
        post.setComment(realmPost.getComment());
        post.setPublishedAt(realmPost.getPublishedAt());
        post.setPhoto(realmPost.getPhoto());

        return post;
    }

}
