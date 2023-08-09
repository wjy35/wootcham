package com.ssafy.wcc.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Error {

    // 사용자
    USER_NOT_FOUND("해당 정보를 가진 사용자가 없습니다.", HttpStatus.NOT_FOUND),
    NOT_ADMIN("관리자가 아닙니다.", HttpStatus.FORBIDDEN),
    PASSWORD_NOT_MATCH("비밀번호가 일치하지 않습니다", HttpStatus.BAD_REQUEST),
    SUSPENDED_USER("정지된 유저입니다.", HttpStatus.FORBIDDEN),

    // 공지 사항
    NO_SUCH_NOTICE("해당 아이디를 가진 공지사항이 없습니다.", HttpStatus.NOT_FOUND),

    // 토큰
    TOKEN_EXPIRED("토큰이 만료되었습니다.",HttpStatus.UNAUTHORIZED);
    private final String message;
    private final HttpStatus errorCode;

    Error(String message, HttpStatus errorCode) {
        this.message = message;
        this.errorCode = errorCode;
        System.out.println("message = " + message);
        System.out.println("errorCode = " + errorCode);
    }
}
