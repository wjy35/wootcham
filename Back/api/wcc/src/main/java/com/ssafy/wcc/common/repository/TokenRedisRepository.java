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

    public void saveToken(String token, String value, Long timeout) {
        ValueOperations<String, String> valueOperations = redisTokenTemplate.opsForValue();
        valueOperations.set(token, value);
        redisTokenTemplate.expire(token, timeout, TimeUnit.MILLISECONDS);
    }

    public String getTokenValue(String key) {
        ValueOperations<String, String> valueOperations = redisTokenTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void deleteId(String id) {
        redisTokenTemplate.delete(id);
    }

    public void saveId(String id, String nickname, Long timeout) {
        ValueOperations<String, String> valueOperations = redisTokenTemplate.opsForValue();
        valueOperations.set(id, nickname);
        redisTokenTemplate.expire(id, timeout, TimeUnit.MILLISECONDS);
    }
}
