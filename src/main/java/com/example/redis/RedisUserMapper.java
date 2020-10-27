package com.example.redis;

import com.example.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class RedisUserMapper {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void setUser(String key, Object value) {
        ValueOperations<String, Object> obj = redisTemplate.opsForValue();
        obj.set(key, value);
    }

    public Member getUser(String key) {
        ValueOperations<String, Object> obj = redisTemplate.opsForValue();
        Member value = (Member) obj.get(key);

        return value;
    }

    public void delUser(String key) {
        redisTemplate.delete(key);
    }
}
