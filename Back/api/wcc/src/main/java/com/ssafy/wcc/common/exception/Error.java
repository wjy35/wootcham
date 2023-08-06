package com.ssafy.wcc.common.exception;

import lombok.Getter;

@Getter
public enum Error {

    // 사용자
    USER_NOT_FOUND("해당 정보를 가진 사용자가 없습니다.", 1000),
    NOT_ADMIN("관리자가 아닙니다.", 2000);

    private final String message;
    private final int errorCode;

    Error(String message, int errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
