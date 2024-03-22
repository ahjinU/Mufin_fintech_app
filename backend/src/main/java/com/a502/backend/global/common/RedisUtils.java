package com.a502.backend.global.common;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisUtils {
    private final RedisTemplate<String, Object> redisTemplate;

    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void setData(String key, Object value ,Long expiredTime){
        redisTemplate.opsForList().rightPush(key, value);
//        redisTemplate.opsForList().
//        redisTemplate.opsForList().set(key, value, expiredTime, TimeUnit.MILLISECONDS);
    }

    public Object getData(String key){
        return redisTemplate.opsForList().getOperations().opsForValue().get("ranks");
    }

    public void deleteData(String key){
        redisTemplate.delete(key);
    }
}
