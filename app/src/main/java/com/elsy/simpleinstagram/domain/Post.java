package com.elsy.simpleinstagram.domain;

import com.elsy.simpleinstagram.data.APIConstants;
import com.google.gson.annotations.SerializedName;

public class Post {

    @SerializedName(APIConstants.Keys.TITLE)
    private String title;

    @SerializedName(APIConstants.Keys.PUBLISHED_AT)
    private String publishedAt;

    @SerializedName(APIConstants.Keys.PHOTO)
    private String photo;

    @SerializedName(APIConstants.Keys.ID)
    private int id;

    @SerializedName(APIConstants.Keys.COMMENT)
    private String comment;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
