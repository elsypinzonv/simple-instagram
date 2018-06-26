package com.elsy.simpleinstagram.data.local;

import android.support.annotation.NonNull;

import com.elsy.simpleinstagram.data.local.realm.RealmCallback;
import com.elsy.simpleinstagram.data.remote.callbacks.ListCallback;
import com.elsy.simpleinstagram.data.local.realm.RealmRepository;
import com.elsy.simpleinstagram.data.local.realm.mappers.Mapper;
import com.elsy.simpleinstagram.data.local.realm.models.RealmPost;
import com.elsy.simpleinstagram.data.local.realm.specification.AllRealmSpecification;
import com.elsy.simpleinstagram.data.remote.callbacks.ServerCallback;
import com.elsy.simpleinstagram.domain.Post;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;


public class PostLocalRepository extends RealmRepository<RealmPost, Post> {

    private static PostLocalRepository INSTANCE;
    private static final String ERROR_MESSAGE = "NOT VALUES FOUND";

    private PostLocalRepository(RealmConfiguration realmConfiguration, Mapper mapper) {
        super(realmConfiguration, mapper);
    }

    public static PostLocalRepository getInstance(RealmConfiguration realmConfiguration, Mapper mapper) {
        if (INSTANCE == null) {
            INSTANCE = new PostLocalRepository(realmConfiguration, mapper);
        }
        return INSTANCE;
    }

    /**
     * Save a object into local database. Executes individual transaction on Realm database.
     * @param post object to persist locally.
     */
    public void add(final Post post, final RealmCallback realmCallback) {
        final Realm realm = Realm.getInstance(realmConfiguration);

        realm.executeTransactionAsync(
                new Realm.Transaction() {
                    @Override
                    public void execute(@NonNull Realm realm) {
                        final RealmPost realmPost = realm.createObject(RealmPost.class, post.getId());
                        realmPost.setComment(post.getComment());
                        realmPost.setPhoto(post.getPhoto());
                        realmPost.setPublishedAt(post.getPublishedAt());
                        realmPost.setTitle(post.getTitle());
                    }
                },
                new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        realmCallback.onSuccessRealmAction();
                    }
                },
                new Realm.Transaction.OnError() {
                    @Override
                    public void onError(@NonNull Throwable error) {
                        realmCallback.onErrorRealmAction(error.getMessage());
                    }
                }
        );

        realm.close();
    }

    /**
     * Retrieves all objects from local database.
     * {@link ServerCallback#onServerError(String)}
     * is fired if the the table is empty.
     * @param callback responder of transaction.
     */
    @Override
    public void getAll(final ListCallback<Post> callback) {
        final RealmSpecification<RealmPost> realmSpecification = new AllRealmSpecification();

        final Realm realm = Realm.getInstance(realmConfiguration);
        final RealmResults<RealmPost> realmResults = realmSpecification.toRealmResults(realm);

        if(realmResults.isEmpty()){
            callback.onServerError(ERROR_MESSAGE);
        } else {
            final List<Post> pets = new ArrayList<>();

            for (RealmPost realmPost : realmResults) {
                pets.add(toDomainEntityMapper.map(realmPost));
            }

            realm.close();
            callback.onItemsLoaded(pets);
        }
    }

}
