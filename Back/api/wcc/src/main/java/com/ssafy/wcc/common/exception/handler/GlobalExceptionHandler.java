package com.ssafy.wcc.common.exception.handler;

import com.ssafy.wcc.common.exception.WCCException;
import com.ssafy.wcc.common.exception.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(WCCException.class)
    public ResponseEntity<ErrorResponse> handleWCCException(WCCException e) {
        log.info("error 발생: {}, {}", e.getError().getErrorCode(), e.getError().getMessage());
        return ErrorResponse.toResponseEntity(e);
    }
}
