package com.elsy.simpleinstagram.data.interactors;

import static com.elsy.simpleinstagram.utils.ActivityHelper.checkNotNull;

public abstract class BaseInteractor<ConcreteService> {

    protected final ConcreteService apiService;

    public BaseInteractor(
            ConcreteService sessionsAPIService
    ) {
        this.apiService = checkNotNull(sessionsAPIService);
    }

}