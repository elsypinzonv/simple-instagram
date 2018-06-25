package com.elsy.simpleinstagram.data;

import static com.elsy.simpleinstagram.utils.ActivityHelper.checkNotNull;

public abstract class BaseInteractor<ConcreteService> {

    protected ConcreteService apiService;

    public BaseInteractor(
            ConcreteService sessionsAPIService
    ) {
        this.apiService = checkNotNull(sessionsAPIService);
    }

}