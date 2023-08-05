package com.ssafy.wcc.domain.jwt.application.service;

import com.ssafy.wcc.common.repository.AccessTokenRedisRepository;
import com.ssafy.wcc.common.repository.BlackListTokenRedisRepository;
import com.ssafy.wcc.common.repository.EmailRedisRepository;
import com.ssafy.wcc.common.repository.RefreshTokenRedisRepository;
import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;
import com.ssafy.wcc.domain.member.db.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{

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
        String accessToken = create("access-token", expireMin);
        accessTokenRedisRepository.saveAccessToken(accessToken, id, expireMin);
        return accessToken;
    }

    @Override
    public String createRefreshToken(String id) {
        String refreshToken = create("refresh-token", expireMin*5);
        refreshTokenRedisRepository.saveRefreshToken(refreshToken, id, expireMin*5);
        return refreshToken;
    }

    @Override
    public void saveLogoutToken(String accessToken){
        blackListTokenRedisRepository.saveBlackListToken(accessToken, "logout", this.getExpire(accessToken));
    }


    @Override
    public Long getExpire(String accessToken){
        Date expiration = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(accessToken).getBody().getExpiration();
        // 현재 시간
        Long now = new Date().getTime();
        return (expiration.getTime() - now);
    }

    @Override
    public String create(String subject, long expireMin) {
        Claims claims = Jwts.claims()
                .setSubject(subject)
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
    public String getAccessTokenId(String token){
        return accessTokenRedisRepository.getAccessTokenValue(token);
    }

    @Override
    public String getBlackListTokenId(String token) {
        return blackListTokenRedisRepository.getBlackListTokenValue(token);
    }

    @Override
    public String getRefreshTokenId(String token) {
        return refreshTokenRedisRepository.getRefreshTokenValue(token);
    }

    @Override
    public String getEmailData(String token) {
        return emailRedisRepository.getEmailValue(token);
    }

    @Override
    public void deleteRefreshToken(String refreshToken){
        refreshTokenRedisRepository.deleteRefreshToken(refreshToken);
    }

    @Override
    public byte[] generateKey() {
        byte[] key = null;
        key = salt.getBytes();
        return key;
    }

    @Override
    public Map<String, Object> checkAndGetClaims(String jwt) {
        Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
        return claims.getBody();
    }

    @Override
    public boolean checkToken(String jwt) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public MemberLoginResponse makeMemberLoginResponse(String id) {
        MemberLoginResponse response = new MemberLoginResponse(this.createAccessToken(id),this.createRefreshToken(id));
        return response;
    }

    @Override
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("access-token");
    }

    @Override
    public Authentication getAuthentication(String token) {
        String id = this.getAccessTokenId(token);
        Member m = Member.builder().id  (Long.parseLong(id)).build();
        UserDetails userDetails = m;
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

//    @Override
//    public String getEmail(String token){
//      return (String)Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token).getBody().get("email");
//    }
//


}
