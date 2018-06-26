package com.elsy.simpleinstagram.app.newPost;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.elsy.simpleinstagram.Injection;
import com.elsy.simpleinstagram.R;
import com.elsy.simpleinstagram.utils.ActivityHelper;

import java.io.File;

import static com.elsy.simpleinstagram.utils.ActivityHelper.checkNotNull;

public class NewPostActivity extends AppCompatActivity implements NewPostContract.NewPostView {

    private Toolbar toolbar;
    private ImageView picture;
    private TextInputEditText title;
    private TextInputEditText comment;
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
    public void showPicture(File file) {
        ActivityHelper.setImageWithPicasso(file, picture);
    }

    @Override
    public void sendToHome() {
        finish();
    }

    @Override
    public void showFailedLoadMessage(String error) {
        Snackbar.make(comment, error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showValidationErrors(String error) {
        if(title.getText().toString().isEmpty()){
            title.setError(error);
        }
        if(comment.getText().toString().isEmpty()){
            comment.setError(error);
        }

    }

    private void setUpPresenter() {
        presenter = new NewPostPresenter(this, Injection.providePostsRepository(this));
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
            presenter.savePost(title.getText().toString(), comment.getText().toString());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initToolbar(){
        setSupportActionBar(toolbar);
        checkNotNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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
        comment = findViewById(R.id.comment);
    }
}
