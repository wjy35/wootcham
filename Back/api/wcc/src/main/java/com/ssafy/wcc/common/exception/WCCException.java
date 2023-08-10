package com.ssafy.wcc.common.exception;

import lombok.Getter;

@Getter
public class WCCException extends RuntimeException {
    private final Error error;

    public WCCException(Error error) {
        this.error = error;
    }
}
