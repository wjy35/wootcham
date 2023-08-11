package com.ssafy.wcc.domain.jwt.application.service;

import com.ssafy.wcc.common.exception.Error;
import com.ssafy.wcc.common.repository.AccessTokenRedisRepository;
import com.ssafy.wcc.common.repository.BlackListTokenRedisRepository;
import com.ssafy.wcc.common.repository.EmailRedisRepository;
import com.ssafy.wcc.common.repository.RefreshTokenRedisRepository;
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
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);

    private final CustomUserDetailService customUserDetailService;
    private final EmailRedisRepository emailRedisRepository;
    private final AccessTokenRedisRepository accessTokenRedisRepository;
    private final RefreshTokenRedisRepository refreshTokenRedisRepository;
    private final BlackListTokenRedisRepository blackListTokenRedisRepository;

    @Value("${jwt.salt}")
    private String salt;

    @Value("${jwt.expmin}")
    private Long expireMin;

    @Override
    public String createAccessToken(String id) {
        logger.info("createAccessToken service 진입");
        String accessToken = create("accessToken", id, expireMin);
        return accessToken;
    }

    @Override
    public String createRefreshToken(String id) {
        logger.info("createRefreshToken service 진입");
        String refreshToken = create("refreshToken", id, expireMin * 5);
        return refreshToken;
    }

    @Override
    public void saveLogoutToken(String accessToken) {
        logger.info("saveLogoutToken service 진입");
        blackListTokenRedisRepository.saveBlackListToken(accessToken, "logout", this.getExpire(accessToken));
    }


    @Override
    public Long getExpire(String accessToken) {
        logger.info("getExpire service 진입");
        System.out.println(accessToken);
        Date expiration = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(accessToken).getBody().getExpiration();
        Long now = new Date().getTime();
        return (expiration.getTime() - now);
    }

    @Override
    public String create(String subject, String id, long expireMin) {
        logger.info("create service 진입");
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
    public String getBlackListTokenId(String token) {
        logger.info("getBlackListTokenId service 진입");
        return blackListTokenRedisRepository.getBlackListTokenValue(token);
    }

    @Override
    public String getEmailData(String token) {
        logger.info("getEmailData service 진입");
        return emailRedisRepository.getEmailValue(token);
    }

    @Override
    public void deleteRefreshToken(String refreshToken) {
        logger.info("deleteRefreshToken service 진입");
        refreshTokenRedisRepository.deleteRefreshToken(refreshToken);
    }

    @Override
    public byte[] generateKey() {
        logger.info("generateKey service 진입");
        byte[] key = null;
        key = salt.getBytes();
        return key;
    }

    @Override
    public String getIdByToken(String jwt) {
        logger.info("checkAndGetClaims service 진입");
        Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
        return claims.getBody().getId();
    }

    @Override
    public boolean checkToken(String jwt){
        logger.info("checkToken service 진입");
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
            return true;
        } catch (SignatureException e) {
            log.info("SignatureException");
            throw new JwtException(Error.WRONG_TYPE_TOKEN.getMessage());
        } catch (MalformedJwtException e) {
            log.info("MalformedJwtException");
            throw new JwtException(Error.UNSUPPORTED_TOKEN.getMessage());
        } catch (ExpiredJwtException e) {
            log.info("ExpiredJwtException");
            throw new JwtException(Error.EXPIRED_TOKEN.getMessage());
        } catch (IllegalArgumentException e) {
            log.info("IllegalArgumentException");
            throw new JwtException(Error.UNKNOWN_ERROR.getMessage());
        }
    }

    @Override
    public MemberLoginResponse makeMemberLoginResponse(String id) {
        logger.info("makeMemberLoginResponse service 진입");
        MemberLoginResponse response = new MemberLoginResponse( this.createAccessToken(id), this.createRefreshToken(id));
        return response;
    }

    @Override
    public String resolveToken(HttpServletRequest request) {
        logger.info("resolveToken service 진입");
        return request.getHeader("Authorization");
    }

    @Override
    public Authentication getAuthentication(String token) throws RuntimeException {
        logger.info("getAuthentication service 진입");
        String id = this.getIdByToken(token);
        Member m = Member.builder().id(Long.parseLong(id)).build();
        UserDetails userDetails = m;
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

}
