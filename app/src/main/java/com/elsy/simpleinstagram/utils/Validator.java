package com.elsy.simpleinstagram.utils;

import com.elsy.simpleinstagram.data.APIConstants;

public class Validator {

    public static final boolean isValidHostame(String hostname){
        return APIConstants.URL.contains(hostname);
    }
}
