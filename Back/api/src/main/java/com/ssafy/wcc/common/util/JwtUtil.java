package com.ssafy.wcc.common.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtil {
    @Value("${jwt.salt}")
    private String salt;

    @Value("${jwt.expmin}")
    private Long expireMin;

    public String createAccessToken(String id){
        return create(id, "access-token", expireMin);
    }

    public String createRefreshToken(String id){
        return create(null, "refresh-token", expireMin*5);

    }

    private String create(String id, String subject, long expireMin){
        Claims claims = Jwts.claims()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireMin));

        claims.put("member_id", id);

        String jwt = Jwts.builder()
                .setHeaderParam("typ", "jwtToken")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact();
        return jwt;
    }

    private byte[] generateKey() {
        byte[] key = null;
        try {
            key = salt.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return key;
    }

    public Map<String, Object> checkAndGetClaims(String jwt){
        Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
        return claims.getBody();
    }

    public boolean checkToken(String jwt) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
