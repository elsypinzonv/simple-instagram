package com.elsy.simpleinstagram.interactor;

import static com.elsy.simpleinstagram.util.ActivityHelper.checkNotNull;

public abstract class BaseInteractor<ConcreteService> {

    protected final ConcreteService apiService;

    public BaseInteractor(
            ConcreteService sessionsAPIService
    ) {
        this.apiService = checkNotNull(sessionsAPIService);
    }

}