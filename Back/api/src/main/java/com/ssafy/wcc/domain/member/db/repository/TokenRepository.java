package com.ssafy.wcc.domain.member.db.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Repository
@RequiredArgsConstructor
public class TokenRepository {

    private final RedisTemplate<String, String> redisTokenTemplate;

    public void save(String refreshToken, String value){
        ValueOperations<String, String> valueOperations = redisTokenTemplate.opsForValue();
        String key = "Token:"+refreshToken;
        valueOperations.set(key, value);
        redisTokenTemplate.expire(key, 60, TimeUnit.SECONDS);
    }

    public void delete(String refreshToken){
        redisTokenTemplate.delete(refreshToken);
    }

    public Optional<String> findIdByRefreshToken(String refreshToken) {
        ValueOperations<String, String> valueOperations = redisTokenTemplate.opsForValue();
        String memberId = valueOperations.get(refreshToken);

        if (Objects.isNull(memberId)) {
            return Optional.empty();
        }
        return Optional.of(memberId);
    }

}
