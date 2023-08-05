package com.ssafy.wcc.common.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class BlackTokenRedisConfig extends RedisConfig{
    @Bean
    @Primary
    public RedisConnectionFactory tokenRedisConnectionFactory() {
        return createLettuceConnectionFactory(2);
    }

    @Bean
    @Qualifier("redisBlackListTokenTemplate")
    public RedisTemplate<?, ?> redisBlackListTokenTemplate() {
        RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(tokenRedisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(String.class));
        return redisTemplate;
    }
}
