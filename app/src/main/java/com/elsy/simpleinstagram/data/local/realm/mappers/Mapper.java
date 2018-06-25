package com.elsy.simpleinstagram.data.local.realm.mappers;

public interface Mapper<From, To> {
    To map(From from);
}