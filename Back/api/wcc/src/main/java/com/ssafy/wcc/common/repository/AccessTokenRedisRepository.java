package com.ssafy.wcc.common.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class AccessTokenRedisRepository {

    private final RedisTemplate<String, String> redisAccessTemplate;

    public void saveAccessToken(String accessToken, String value, Long timeout) {
        ValueOperations<String, String> valueOperations = redisAccessTemplate.opsForValue();
        valueOperations.set("AccessToken: " + accessToken, value);
        redisAccessTemplate.expire("AccessToken: " + accessToken, timeout, TimeUnit.SECONDS);
    }

    public String getAccessTokenValue(String key) {
        ValueOperations<String, String> valueOperations = redisAccessTemplate.opsForValue();
        return valueOperations.get("AccessToken: " + key);
    }

    public void deleteAccessToken(String accessToken) {
        redisAccessTemplate.delete(accessToken);
    }
}
