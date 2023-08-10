package com.ssafy.wcc.common.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.wcc.common.exception.Error;
import com.ssafy.wcc.common.exception.dto.ErrorResponse;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtExceptionFilter extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(JwtExceptionFilter.class);

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        try {
            chain.doFilter(request, response);
        } catch (JwtException ex) {
            String message = ex.getMessage();
            if(Error.UNKNOWN_ERROR.getMessage().equals(message)) {
                logger.info("인증 토큰이 존재하지 않습니다.");
                setResponse(response, Error.UNKNOWN_ERROR);
            }
            //잘못된 타입의 토큰인 경우
            else if(Error.WRONG_TYPE_TOKEN.getMessage().equals(message)) {
                logger.info("잘못된 토큰 정보입니다.");
                setResponse(response, Error.WRONG_TYPE_TOKEN);
            }
            //토큰 만료된 경우
            else if(Error.EXPIRED_TOKEN.getMessage().equals(message)) {
                logger.info("만료된 토큰 정보입니다.");
                setResponse(response, Error.EXPIRED_TOKEN);
            }
            //지원되지 않는 토큰인 경우
            else if(Error.UNSUPPORTED_TOKEN.getMessage().equals(message)) {
                logger.info("지원하지 않는 토큰 방식입니다.");
                setResponse(response, Error.UNSUPPORTED_TOKEN);
            }
            else {
                logger.info("알 수 없는 이유로 요청이 거절되었습니다.");
                setResponse(response, Error.ACCESS_DENIED);
            }
        }

    }

    private void setResponse(HttpServletResponse response, Error errorMessage) throws RuntimeException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(errorMessage.getErrorCode().value());

        // HelloData 객체
        ErrorResponse errorResponse = ErrorResponse.builder().isSuccess("false").build();

        // json 형태로 바꾸기
        String result = mapper.writeValueAsString(errorResponse);
        response.getWriter().write(result);
    }
}
