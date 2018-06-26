package com.elsy.simpleinstagram.app.PostDetail;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.elsy.simpleinstagram.Injection;
import com.elsy.simpleinstagram.R;
import com.elsy.simpleinstagram.domain.Post;
import com.squareup.picasso.Picasso;

import java.io.File;

public class PostDetailActivity extends AppCompatActivity implements PostDetailContract.PostDetailView {

    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private TextView idText;
    private TextView createdAtText;
    private TextView commentText;
    private ImageView postImage;
    private PostDetailPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_detail);
        bindViews();
        initToolbar();
        setUpPresenter();
        presenter.loadPost();
    }


    @Override
    public void showPost(Post post) {
        setData(post);
    }

    @Override
    public void showFailedLoadMessage(String error) {
        Snackbar.make(collapsingToolbar, error, Snackbar.LENGTH_LONG).show();
    }

    private void setData(Post post) {
        collapsingToolbar.setTitle(post.getTitle());
        idText.setText(String.valueOf(post.getId()));
        createdAtText.setText(post.getPublishedAt());
        commentText.setText(post.getComment());
        presenter.loadImage(post.getPhoto());
    }

    @Override
    public void setPicture(File imageFile){
        Picasso.get()
                .load(imageFile)
                .resize(400, 0)
                .centerCrop()
                .into(postImage);
    }

    @Override
    public void setPicture(String url){
        Picasso.get()
                .load(url)
                .into(postImage);
    }

    private void setUpPresenter(){
        presenter = new PostDetailPresenter(
                this,
                Injection.provideGSON()
        );
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void bindViews(){
        toolbar = findViewById(R.id.toolbar);
        collapsingToolbar = findViewById(R.id.toolbar_layout);
        idText = findViewById(R.id.id);
        createdAtText = findViewById(R.id.created_at);
        commentText = findViewById(R.id.comment);
        postImage = findViewById(R.id.post_picture);
    }

}
