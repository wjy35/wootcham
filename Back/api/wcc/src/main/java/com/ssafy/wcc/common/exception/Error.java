package com.ssafy.wcc.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum Error {

    // 사용자
    USER_NOT_FOUND("해당 정보를 가진 사용자가 없습니다.", HttpStatus.NOT_FOUND),
    NOT_ADMIN("관리자가 아닙니다.", HttpStatus.UNAUTHORIZED),

    // 공지 사항
    NO_SUCH_NOTICE("해당 아이다를 가진 공지사항이 없습니다.", HttpStatus.NOT_FOUND);

    private final String message;
    private final HttpStatus errorCode;

    Error(String message, HttpStatus errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
