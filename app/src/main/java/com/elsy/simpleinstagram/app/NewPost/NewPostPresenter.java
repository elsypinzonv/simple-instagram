package com.elsy.simpleinstagram.app.NewPost;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import com.elsy.simpleinstagram.data.common.PostsRepository;
import com.elsy.simpleinstagram.data.local.realm.RealmCallback;
import com.elsy.simpleinstagram.domain.Post;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewPostPresenter implements NewPostContract.UserActionsListener, RealmCallback {


    private Activity activity;
    private NewPostContract.NewPostView view;
    private PostsRepository repository;
    private String ERROR_EMPTY= "This field is obligatory";
    private String filePath;

    public NewPostPresenter(NewPostContract.NewPostView view, PostsRepository repository) {
        this.view = view;
        this.activity = ((AppCompatActivity) view);
        this.repository = repository;
    }


    @Override
    public void getPicture() {
        String path =  activity.getIntent().getStringExtra("filepath");
        this.filePath = path;
        if(path != null){
            Uri imageUri = Uri.parse(path);
            File imgFile = new File(imageUri.getPath());
            if(imgFile.exists()) {
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(imgFile.getAbsolutePath(), options);
                int imageHeight = options.outHeight;
                int imageWidth = options.outWidth;
                if (imageHeight > 4096 || imageWidth > 4096) {
                    BitmapFactory.Options opts = new BitmapFactory.Options();
                    opts.inSampleSize = 4;
                    Bitmap bitmap = BitmapFactory.decodeFile(imageUri.toString(), opts);
                    view.showPicture(bitmap);
                } else {
                    view.showPicture(imgFile);
                }
            } else view.showFailedLoadMessage("Error getting the picture");
        }
    }

    @Override
    public void savePost(String title, String comment) {
        if(!title.isEmpty() && !comment.isEmpty()){
            Post post = new Post();
            post.setId(getID());
            post.setPhoto(filePath);
            post.setTitle(title);
            post.setComment(comment);
            post.setPublishedAt(getpublishedAt());
            repository.add(post, this);
        }else view.showValidationErrors(ERROR_EMPTY);
    }

    private String getID() {
        return new SimpleDateFormat(
                "yyyyMMddHHmmss"
        ).format(new Date());

    }

    private String getpublishedAt() {
        return new SimpleDateFormat(
                "yyyy-MM-dd'T'HH:mm:ssZ"
        ).format(new Date());
    }

    @Override
    public void onSuccessRealmAction() {
        view.sendToHome();
    }

    @Override
    public void onErrorRealmAction(String serverErrorMessage) {
        view.showFailedLoadMessage(serverErrorMessage);
    }
}
