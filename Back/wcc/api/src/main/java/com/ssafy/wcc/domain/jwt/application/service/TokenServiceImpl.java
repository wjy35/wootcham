package com.ssafy.wcc.domain.jwt.application.service;

import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;
import com.ssafy.wcc.domain.jwt.db.repository.TokenRepository;
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
    private final TokenRepository tokenRepository;

    @Value("${jwt.salt}")
    private String salt;

    @Value("${jwt.expmin}")
    private Long expireMin;

    @Override
    public String createAccessToken(String email) {
        return create(email, "access-token", expireMin);
    }

    @Override
    public String createRefreshToken(String email) {
        String refreshToken = create(null, "refresh-token", expireMin*5);
        tokenRepository.save(refreshToken, email);
        return refreshToken;
    }


    @Override
    public String create(String email, String subject, long expireMin) {
        Claims claims = Jwts.claims()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireMin));

        claims.put("email", email);

        String jwt = Jwts.builder()
                .setHeaderParam("typ", "jwtToken")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, this.generateKey())
                .compact();
        return jwt;
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
    public MemberLoginResponse makeMemberLoginResponse(String email) {
        MemberLoginResponse response = new MemberLoginResponse(this.createAccessToken(email),this.createRefreshToken(email));
        return response;
    }

    @Override
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("access-token");
    }

    @Override
    public Authentication getAuthentication(String token) {
        String email = (String)Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token).getBody().get("email");
        Member m = Member.builder().email(email).build();
        UserDetails userDetails = m;
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    @Override
    public String getUserPk(String token) {
        String st = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token).getBody().getSubject();
        return st;
    }
}
