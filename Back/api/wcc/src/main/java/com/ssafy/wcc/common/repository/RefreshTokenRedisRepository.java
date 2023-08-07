package com.ssafy.wcc.common.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class RefreshTokenRedisRepository {

    private final RedisTemplate<String, String> redisRefreshTokenTemplate;

    public void saveRefreshToken(String refreshToken, String value, Long timeout) {
        ValueOperations<String, String> valueOperations = redisRefreshTokenTemplate.opsForValue();
        valueOperations.set("RefreshToken: "+refreshToken, value);
        redisRefreshTokenTemplate.expire("RefreshToken: "+refreshToken, timeout, TimeUnit.MILLISECONDS);
    }

    public String getRefreshTokenValue(String key) {
        ValueOperations<String, String> valueOperations = redisRefreshTokenTemplate.opsForValue();
        return valueOperations.get("RefreshToken: "+key);
    }

    public void deleteRefreshToken(String refreshToken) {
        redisRefreshTokenTemplate.delete("RefreshToken: "+refreshToken);
    }
}
