package com.ssafy.wcc.domain.jwt.application.service;

import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;

public interface TokenService {
    public String createAccessToken(String id);
    public String createRefreshToken(String id);

    public String create(String subject, String id, long expireMin);

    public void saveLogoutToken(String id, String accessToken);

    public Long getExpire(String accessToken);

    public byte[] generateKey();

    public String getIdByToken(String jwt);

    public boolean checkToken(String jwt) throws ExpiredJwtException;

    public MemberLoginResponse makeMemberLoginResponse(String id, String nickName);

    public String resolveToken(HttpServletRequest request);

    public Authentication getAuthentication(String token);

    public String getValue(String token);

    public String getEmailData(String token);
}
