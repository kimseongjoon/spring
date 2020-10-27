package com.example.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    // application.properties에서 정보 가져오기
    private @Value("${spring.redis.host}") String HOST;
    private @Value("${spring.redis.port}") int PORT;
    private @Value("${spring.redis.password}") String PASSWORD;
    private @Value("${spring.redis.database}") int DATABASE;

    // 연결 설정
    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(HOST);
        config.setPort(PORT);
        config.setPassword(PASSWORD);
        config.setDatabase(DATABASE);

        LettuceConnectionFactory lcf = new LettuceConnectionFactory(config);

        return lcf;
    }

    //입력하거나 가져오기 위한 데이터 타입
    @Bean // RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        return redisTemplate;
    }
}
