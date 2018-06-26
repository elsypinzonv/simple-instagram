package com.elsy.simpleinstagram;

import android.app.Activity;
import android.content.Context;

import com.elsy.simpleinstagram.data.PostsInteractor;
import com.elsy.simpleinstagram.data.common.PostsRepository;
import com.elsy.simpleinstagram.data.local.PostLocalRepository;
import com.elsy.simpleinstagram.data.local.realm.mappers.RealmPostToPostMapper;
import com.elsy.simpleinstagram.data.remote.PostRemoteRepository;
import com.elsy.simpleinstagram.data.remote.service.PostsService;
import com.elsy.simpleinstagram.data.remote.service.ServiceGenerator;
import com.elsy.simpleinstagram.utils.camera.CameraHelper;
import com.google.gson.Gson;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class Injection {

    public static Gson provideGSON() {
        return new Gson();
    }

    public static CameraHelper provideCameraHelper(Activity activity) {
        return new CameraHelper(activity);
    }

    public static PostsRepository providePostsRepository(Context context) {
        Realm.init(context);
        return PostsRepository.getInstance(
                providePostRemoteRepository(),
                providePostLocalRepository()
        );
    }

    private static PostRemoteRepository providePostRemoteRepository() {
        return new PostRemoteRepository(providePostInteractor());
    }

    private static PostLocalRepository providePostLocalRepository() {
        return PostLocalRepository.getInstance(
                new RealmConfiguration.Builder().build(),
                new RealmPostToPostMapper()
        );
    }

    private static PostsInteractor providePostInteractor(){
        return new PostsInteractor(providePostService());
    }

    private static PostsService providePostService(){
        return ServiceGenerator.createService(PostsService.class);
    }
}
