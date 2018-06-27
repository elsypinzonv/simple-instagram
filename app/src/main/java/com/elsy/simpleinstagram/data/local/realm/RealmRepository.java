package com.elsy.simpleinstagram.data.local.realm;


import com.elsy.simpleinstagram.data.common.Repository;
import com.elsy.simpleinstagram.data.local.realm.mappers.Mapper;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Realm based local persistence main entry point. Only local persistence which is
 * Realm based specific should inherit this class.
 */
public abstract class RealmRepository<RealmEntity extends RealmModel, DomainEntity>
        implements Repository<DomainEntity> {

    protected final RealmConfiguration realmConfiguration;
    protected final Mapper<RealmEntity, DomainEntity> toDomainEntityMapper;

    /**
     * Class constructor.
     * @param realmConfiguration setup for specific Realm instance.
     * @param mapper bridge between realm objects and domain model objects.
     */
    protected RealmRepository(final RealmConfiguration realmConfiguration, Mapper<RealmEntity, DomainEntity> mapper) {
        this.realmConfiguration = realmConfiguration;
        this.toDomainEntityMapper = mapper;
    }

    /**
     * Main interface to fetch results on a Realm transaction.
     * @param <RealmEntity> Realm objects class reference to be fetched
     */
    public interface RealmSpecification<RealmEntity extends RealmModel> {
        RealmResults<RealmEntity> toRealmResults(Realm realm);
    }

}

