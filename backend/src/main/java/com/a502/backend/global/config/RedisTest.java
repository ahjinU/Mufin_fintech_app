package com.a502.backend.global.config;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RedisTest{
    private final RedisTemplate<String, String> redisTemplate;

    public String createRecord(int id) {
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("hanseul", "rayeon");
        return "plz";
    }
}

