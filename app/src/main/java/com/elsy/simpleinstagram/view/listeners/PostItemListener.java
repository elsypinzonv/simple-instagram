package com.elsy.simpleinstagram.view.listeners;

import android.view.View;

import com.elsy.simpleinstagram.domain.Post;

public interface PostItemListener {

    void onPostClick(Post clickedPost, View view);

}
