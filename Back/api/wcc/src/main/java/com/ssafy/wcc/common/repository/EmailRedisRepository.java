package com.ssafy.wcc.common.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class EmailRedisRepository {

    private final RedisTemplate<String, String> redisEmailTemplate;

    public void saveEmail(String email, String value, Long timeout) {
        ValueOperations<String, String> valueOperations = redisEmailTemplate.opsForValue();
        valueOperations.set(email, value);
        redisEmailTemplate.expire(email, timeout, TimeUnit.MILLISECONDS);
    }

    public String getEmailValue(String key) {
        ValueOperations<String, String> valueOperations = redisEmailTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void setDataExpire(String key, String value, long duration) {
        ValueOperations<String, String> valueOperations = redisEmailTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);
        valueOperations.set(key, value, expireDuration);
    }

    public void deleteEmail(String refreshToken) {
        redisEmailTemplate.delete(refreshToken);
    }
}
