package com.ssafy.wcc.domain.jwt.db.repository;

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

    public void save(String refreshToken, String value, Long timeout){
        ValueOperations<String, String> valueOperations = redisTokenTemplate.opsForValue();
        String key = refreshToken;
        valueOperations.set(key, value);
        redisTokenTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    public String getData(String key) {
        ValueOperations<String, String> valueOperations = redisTokenTemplate.opsForValue();
        return valueOperations.get(key);
    }

    public void delete(String refreshToken){
        redisTokenTemplate.delete(refreshToken);
    }

//    public Optional<String> findEmailByRefreshToken(String refreshToken) {
//        ValueOperations<String, String> valueOperations = redisTokenTemplate.opsForValue();
//        String memberEmail = valueOperations.get(refreshToken);
//
//        if (Objects.isNull(memberEmail)) {
//            return Optional.empty();
//        }
//        return Optional.of(memberEmail);
//    }

}
