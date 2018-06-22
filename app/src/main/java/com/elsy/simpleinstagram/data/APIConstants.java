package com.elsy.simpleinstagram.data;

public class APIConstants {

    public static final String URL = "https://photomaton.herokuapp.com";
    private static final String API_PATH = "/api/photo";
    private static final String API_ENDPOINT = URL + API_PATH;

    public static class Keys {
        public static final String TITLE = "title";
        public static final String PUBLISHED_AT = "publishedAt";
        public static final String PHOTO = "photo";
        public static final String ID = "id";
        public static final String COMMENT = "comment";
    }
}
