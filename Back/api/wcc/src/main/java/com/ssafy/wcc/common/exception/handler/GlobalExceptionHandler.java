package com.ssafy.wcc.common.exception.handler;

import com.ssafy.wcc.common.exception.CustomJwtExpiredException;
import com.ssafy.wcc.common.exception.WCCException;
import com.ssafy.wcc.common.exception.dto.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WCCException.class)
    public ResponseEntity<ErrorResponse> handleWCCException(WCCException e) {
        System.out.println("33");
        log.info("error 발생: {}, {}", e.getError().getErrorCode(), e.getError().getMessage());
        return ErrorResponse.toResponseEntity(e);
    }

//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<ErrorResponse> handleTokenException(ExpiredJwtException e) {
//        log.info("토큰 만료 발생");
//        return ErrorResponse.toTokenExceptionEntity(e);
//    }

//    @ExceptionHandler(RuntimeException.class)
//    public ResponseEntity<ErrorResponse> handleException(RuntimeException e) {
//        log.info("Runtime error 발생");
//        return ErrorResponse.toExceptionEntity(e);
//    }
}
