package com.elsy.simpleinstagram.view.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.elsy.simpleinstagram.view.listeners.PostItemListener;
import com.elsy.simpleinstagram.R;
import com.elsy.simpleinstagram.domain.Post;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostViewHolder> {


    private Context context;
    private List<Post> postList;
    private PostItemListener listener;

    public PostAdapter(
            Context context,
            List<Post> postList,
            PostItemListener listener
    ) {
        this.postList = postList;
        this.context = context;
        this.listener = listener;
    }

    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_post, parent, false);
        PostViewHolder vh = new PostViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        final Post post = postList.get(position);
        setListener(post, holder);
        holder.postTitle.setText(post.getTitle());
        Picasso.get().cancelRequest(holder.postPicture);
        setPicture(post.getPhoto(), holder.postPicture);
    }

    public void replaceData(List<Post> posts) {
        postList = posts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    private void setPicture(String path, ImageView picture) {
        if(path != null){
            Uri imageUri = Uri.parse(path);
            File imgFile = new File(imageUri.getPath());
            if(imgFile.exists()) {
                Picasso.get()
                        .load(imgFile)
                        .resize(1000, 1000)
                        .centerCrop()
                        .into(picture);
            } else{
                Picasso.get()
                        .load(path)
                        .into(picture);
            }
        }
    }

    private void setListener(final Post post, PostViewHolder holder){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPostClick(post, v);
            }
        });
    }
}