package com.elsy.simpleinstagram.app.NewPost;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.elsy.simpleinstagram.R;
import com.elsy.simpleinstagram.domain.Post;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class NewPostActivity extends AppCompatActivity implements NewPostContract.NewPostView {

    private Toolbar toolbar;
    private ImageView picture;
    private TextInputEditText title;
    private TextInputEditText comnent;
    private NewPostPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_post);
        bindViews();
        setUpPresenter();
        initToolbar();
        presenter.getPicture();
    }


    @Override
    public void showPicture(Bitmap bitmap) {
        picture.setImageBitmap(bitmap);
    }

    @Override
    public void showPicture(File file) {
        Picasso.get()
                .load(file)
                .into(picture);
    }

    @Override
    public void showFailedLoadMessage(String error) {

    }

    private void setUpPresenter() {
        presenter = new NewPostPresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_save) {
            presenter.savePost();
            return true;
        }

        return super.onOptionsItemSelected(item);
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
        picture = findViewById(R.id.post_picture);
        title = findViewById(R.id.title);
        comnent = findViewById(R.id.comment);
    }
}
