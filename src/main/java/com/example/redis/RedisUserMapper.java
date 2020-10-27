package com.example.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisUserMapper {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void setUser(String key, Object value) {
        ValueOperations<String, Object> obj = redisTemplate.opsForValue();
        obj.set(key, value);
    }

    public String getUser(String key) {
        ValueOperations<String, Object> obj = redisTemplate.opsForValue();
        String value = (String) obj.get(key);

        return value;
    }
}
