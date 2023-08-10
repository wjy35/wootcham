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
    TOKEN_EXPIRED("토큰이 만료되었습니다.",HttpStatus.UNAUTHORIZED),
    UNKNOWN_ERROR("인증 토큰이 존재하지 않습니다.",HttpStatus.UNAUTHORIZED),
    WRONG_TYPE_TOKEN("잘못된 토큰 정보입니다.",HttpStatus.UNAUTHORIZED),
    EXPIRED_TOKEN("만료된 토큰 정보입니다.",HttpStatus.UNAUTHORIZED),
    UNSUPPORTED_TOKEN("지원하지 않는 토큰 방식입니다.",HttpStatus.UNAUTHORIZED),
    ACCESS_DENIED("알 수 없는 이유로 요청이 거절되었습니다.",HttpStatus.UNAUTHORIZED),

    // 아이템
    NOT_ENOUGH_MONEY("돈이 부족합니다.", HttpStatus.BAD_REQUEST),
    COLLECTION_NOT_FOUND("해당 정보를 가진 사용자가 없습니다.", HttpStatus.NOT_FOUND),
    ITEM_NOT_PURCHASED("구매하지 않은 아이템입니다.", HttpStatus.BAD_REQUEST),

    // 이메일
    EMAIL_SEND_FAILURE("이메일 전송에 실패하였습니다.", HttpStatus.NOT_FOUND),
    DUPLICATE_EMAIL("이미 존재하는 이메일입니다.", HttpStatus.BAD_REQUEST);

    private final String message;
    private final HttpStatus errorCode;

    Error(String message, HttpStatus errorCode) {
        this.message = message;
        this.errorCode = errorCode;
    }
}
