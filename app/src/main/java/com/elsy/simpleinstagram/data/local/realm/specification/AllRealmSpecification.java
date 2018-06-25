package com.elsy.simpleinstagram.data.local.realm.specification;

import com.elsy.simpleinstagram.data.local.realm.RealmRepository;
import com.elsy.simpleinstagram.data.local.realm.models.RealmPost;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * public class AllRealmSpecificationImplementation of real specification to query all clients from local database.
 */

public class AllRealmSpecification implements RealmRepository.RealmSpecification<RealmPost> {

    /**
     * Query all clients {@link RealmPost} from local database table.
     * @param realm current instance
     * @return {@link RealmResults} list with all clients table results.
     */
    @Override
    public RealmResults<RealmPost> toRealmResults(Realm realm) {
        return realm.where(RealmPost.class)
                .findAll();
    }
}
