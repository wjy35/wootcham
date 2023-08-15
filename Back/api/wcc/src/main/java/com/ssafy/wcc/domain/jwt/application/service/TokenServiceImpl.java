package com.ssafy.wcc.domain.jwt.application.service;

import com.ssafy.wcc.common.exception.Error;
import com.ssafy.wcc.common.repository.TokenRedisRepository;
import com.ssafy.wcc.common.repository.EmailRedisRepository;
import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;
import com.ssafy.wcc.domain.member.db.entity.Member;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    private final EmailRedisRepository emailRedisRepository;
    private final TokenRedisRepository tokenRedisRepository;

    @Value("${jwt.salt}")
    private String salt;

    @Value("${jwt.expmin}")
    private Long expireMin;

    @Override
    public String createAccessToken(String id) {
        String accessToken = create("accessToken", id, expireMin);
        return accessToken;
    }

    @Override
    public String createRefreshToken(String id) {
        String refreshToken = create("refreshToken", id, expireMin * 5);
        tokenRedisRepository.saveToken(refreshToken, id, expireMin * 5);
        return refreshToken;
    }

    @Override
    public void saveLogoutToken(String accessToken) {
        tokenRedisRepository.saveToken(accessToken, "logout", this.getExpire(accessToken));
    }


    @Override
    public Long getExpire(String accessToken) {
        System.out.println(accessToken);
        Date expiration = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(accessToken).getBody().getExpiration();
        Long now = new Date().getTime();
        return (expiration.getTime() - now);
    }

    @Override
    public String create(String subject, String id, long expireMin) {
        Claims claims = Jwts.claims()
                .setSubject(subject)
                .setId(id)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireMin));

        String jwt = Jwts.builder()
                .setHeaderParam("typ", "jwtToken")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact();
        return jwt;
    }

    @Override
    public String getToken(String token) {
        return tokenRedisRepository.getTokenValue(token);
    }


    @Override
    public String getEmailData(String token) {
        return emailRedisRepository.getEmailValue(token);
    }

    @Override
    public byte[] generateKey() {
        byte[] key = null;
        key = salt.getBytes();
        return key;
    }

    @Override
    public String getIdByToken(String jwt) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
        return claims.getBody().getId();
    }

    @Override
    public boolean checkToken(String jwt){
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
            return true;
        } catch (SignatureException e) {
            throw new JwtException(Error.WRONG_TYPE_TOKEN.getMessage());
        } catch (MalformedJwtException e) {
            throw new JwtException(Error.UNSUPPORTED_TOKEN.getMessage());
        } catch (ExpiredJwtException e) {
            throw new JwtException(Error.EXPIRED_TOKEN.getMessage());
        } catch (IllegalArgumentException e) {
            throw new JwtException(Error.UNKNOWN_ERROR.getMessage());
        }
    }

    @Override
    public MemberLoginResponse makeMemberLoginResponse(String id) {
        MemberLoginResponse response = new MemberLoginResponse( this.createAccessToken(id), this.createRefreshToken(id));
        return response;
    }

    @Override
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    @Override
    public Authentication getAuthentication(String token) throws RuntimeException {
        String id = this.getIdByToken(token);
        Member m = Member.builder().id(Long.parseLong(id)).build();
        UserDetails userDetails = m;
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
