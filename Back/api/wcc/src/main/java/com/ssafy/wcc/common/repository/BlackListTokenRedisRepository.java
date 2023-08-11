package com.ssafy.wcc.common.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class BlackListTokenRedisRepository {

    private final RedisTemplate<String, String> redisBlackListTokenTemplate;

    public void saveBlackListToken(String blackListToken, String value, Long timeout) {
        ValueOperations<String, String> valueOperations = redisBlackListTokenTemplate.opsForValue();
        valueOperations.set(blackListToken, value);
        redisBlackListTokenTemplate.expire(blackListToken, timeout, TimeUnit.MILLISECONDS);
    }

    public String getBlackListTokenValue(String key) {
        ValueOperations<String, String> valueOperations = redisBlackListTokenTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void deleteBlackListToken(String blackListToken) {
        redisBlackListTokenTemplate.delete(blackListToken);
    }
}
