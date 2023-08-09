package com.ssafy.wcc.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class WCCException extends RuntimeException {
    private final Error error;

    public WCCException(Error error) {
        System.out.println("error = " + error);
        this.error = error;
    }
}
