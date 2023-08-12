package com.ssafy.wcc.common.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class TokenRedisRepository {

    private final RedisTemplate<String, String> redisTokenTemplate;

    public void saveToken(String blackListToken, String value, Long timeout) {
        ValueOperations<String, String> valueOperations = redisTokenTemplate.opsForValue();
        valueOperations.set(blackListToken, value);
        redisTokenTemplate.expire(blackListToken, timeout, TimeUnit.MILLISECONDS);
    }

    public String getTokenValue(String key) {
        ValueOperations<String, String> valueOperations = redisTokenTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void deleteToken(String blackListToken) {
        redisTokenTemplate.delete(blackListToken);
    }
}
