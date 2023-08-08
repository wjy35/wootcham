package com.ssafy.wcc.domain.jwt.application.service;

import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface TokenService {
    public String createAccessToken(String id);
    public String createRefreshToken(String id);

    public String create(String subject, long expireMin);

    public void saveLogoutToken(String accessToken);

    public Long getExpire(String accessToken);

    public byte[] generateKey();

    public Map<String, Object> checkAndGetClaims(String jwt);

    public boolean checkToken(String jwt);

    public MemberLoginResponse makeMemberLoginResponse(String id);

    public String resolveToken(HttpServletRequest request);

    public Authentication getAuthentication(String token);

    public void deleteRefreshToken(String refreshToken);

    public String getAccessTokenId(String token);
    public String getBlackListTokenId(String token);
    public String getRefreshTokenId(String token);

    public String getEmailData(String token);
}
