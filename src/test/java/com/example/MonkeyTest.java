package com.example;

import io.lettuce.core.RedisConnectionException;
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.exceptions.BeanInstantiationException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class MonkeyTest {

    @Inject
    ApplicationContext applicationContext;

    @Inject
    RedisErrorHandler redisErrorHandler;

    @Test
    @DisplayName("error handler count")
    void getCachedValue() {
        int initialCallCount = redisErrorHandler.getCallCount();
        Monkey monkey = null;

        try {
            monkey = applicationContext.getBean(Monkey.class);
        } catch (BeanInstantiationException e) {
            assertNull(monkey);
            assertInstanceOf(RedisConnectionException.class, e.getCause(), "Root cause must be of type RedisConnectionException");
            int callCount = redisErrorHandler.getCallCount();
            assertTrue(callCount > initialCallCount,
                       "RedisErrorHandler call count (" + callCount + ") must be greater than the initial RedisErrorHandler call count (" + initialCallCount + ").");
        }

    }

}