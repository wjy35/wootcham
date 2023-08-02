package com.ssafy.wcc.domain.jwt.application.service;

import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface TokenService {
    public String createAccessToken(String email);
    public String createRefreshToken(String email);

    public String create(String email, String subject, long expireMin);

    public void delete(String refreshToken);

    public void saveLogoutToken(String accessToken);

    public Long getExpire(String accessToken);
    public byte[] generateKey();

    public Map<String, Object> checkAndGetClaims(String jwt);

    public boolean checkToken(String jwt);

    public MemberLoginResponse makeMemberLoginResponse(String email);

    public String resolveToken(HttpServletRequest request);

    public Authentication getAuthentication(String token);

    public String getEmail(String token);

    public String get(String refreshToken);
}
