package com.example;

import io.micronaut.cache.annotation.Cacheable;
import jakarta.inject.Singleton;

@Singleton
public class Monkey {

    public Monkey() {
    }

    @Cacheable(
//            cacheNames = "Monkey"
    )
    public String getCachedValue() {
        return "FOO";
    }

}
