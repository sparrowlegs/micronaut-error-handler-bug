package com.example;

import io.lettuce.core.RedisConnectionException;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.exceptions.ExceptionHandler;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Singleton;

import java.util.concurrent.atomic.AtomicInteger;

@Produces
@Singleton
@Requires(classes = {RedisConnectionException.class, ExceptionHandler.class})
public class RedisErrorHandler implements ExceptionHandler<RedisConnectionException> {

    private final AtomicInteger callCounter;

    public RedisErrorHandler() {
        this.callCounter = new AtomicInteger();
    }

    @Override
    public void handle(RedisConnectionException exception) {
        callCounter.incrementAndGet();
    }

    public int getCallCount() {
        return callCounter.get();
    }
}
