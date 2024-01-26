package com.example;

import io.lettuce.core.RedisConnectionException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@MicronautTest
class RedisErrorHandlerTest {

    @Inject
    RedisErrorHandler redisErrorHandler;

    @Test
    @DisplayName("call counter is incremented when handle method is called")
    void callCounterIsIncrementedWhenHandleMethodIsCalled() {

        assertNotNull(redisErrorHandler);

        int initialCallCount = redisErrorHandler.getCallCount();
        redisErrorHandler.handle(new RedisConnectionException("poop"));
        int callCount = redisErrorHandler.getCallCount();

        assertTrue(callCount > initialCallCount,
                   "Call count (" + callCount + ") must be greater than initial call count (" + initialCallCount + ").");
    }


}