package com.elsy.simpleinstagram.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.elsy.simpleinstagram.R;

public class PostViewHolder extends RecyclerView.ViewHolder {

        ImageView postPicture;
        TextView postTitle;

        public PostViewHolder(View view) {
            super(view);
            postPicture = (ImageView) view.findViewById(R.id.picture);
            postTitle = (TextView) view.findViewById(R.id.title);
        }
}
