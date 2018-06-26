package com.elsy.simpleinstagram.app.Home;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.elsy.simpleinstagram.Injection;
import com.elsy.simpleinstagram.app.NewPost.NewPostActivity;
import com.elsy.simpleinstagram.utils.camera.CameraHelper;
import com.elsy.simpleinstagram.utils.ActivityHelper;
import com.elsy.simpleinstagram.view.adapters.PostAdapter;
import com.elsy.simpleinstagram.view.listeners.PostItemListener;
import com.elsy.simpleinstagram.R;
import com.elsy.simpleinstagram.app.PostDetail.PostDetailActivity;
import com.elsy.simpleinstagram.data.remote.callbacks.ListCallback;
import com.elsy.simpleinstagram.data.PostsInteractor;
import com.elsy.simpleinstagram.data.remote.service.PostsService;
import com.elsy.simpleinstagram.data.remote.service.ServiceGenerator;
import com.elsy.simpleinstagram.domain.Post;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements PostItemListener, HomeContract.HomeView {

    private RecyclerView postRecyclerView;
    private FloatingActionButton addButton;
    private PostAdapter postAdapter;
    private HomePresenter presenter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();
        initRecyclerView();
        setUpPresenter();
        initButton();
        initToolbar();
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.begin();
    }

    @Override
    public void onPostClick(Post clickedPost, View view) {
        Bundle extras = ActivityOptionsCompat
                .makeScaleUpAnimation(view, 0, 0, view.getWidth(), view.getHeight())
                .toBundle();
        extras.putSerializable("post",  new Gson().toJson(clickedPost));
        ActivityHelper.begin(this,PostDetailActivity.class, extras);

    }

    private void initRecyclerView(){
        postAdapter = new PostAdapter(this,  new ArrayList<Post>(0), this);
        postRecyclerView.setAdapter(postAdapter);
        postRecyclerView.setHasFixedSize(true);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void bindViews(){
        postRecyclerView = findViewById(R.id.posts);
        addButton = findViewById(R.id.add_button);
        toolbar = findViewById(R.id.toolbar);
    }

    private void setUpPresenter(){
        presenter = new HomePresenter(
                this,
                Injection.provideCameraHelper(this),
                Injection.providePostsRepository(this)
        );
    }

    private void initButton() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.takePicture();
            }
        });
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher_round);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CameraHelper.REQUEST_PICTURE_CAPTURE && resultCode == RESULT_OK) {
            presenter.shareNewPost();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        presenter.onRequestPermissionResult(requestCode, grantResults);
    }


    @Override
    public void sendToNewPost(Bundle extras) {
        ActivityHelper.begin(this, NewPostActivity.class, extras);
    }


    @Override
    public void showFailedLoadMessage(String error) {
        Snackbar.make(postRecyclerView, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void refreshRecyclerView(List<Post> list) {
        postAdapter.replaceData(list);
    }

}
