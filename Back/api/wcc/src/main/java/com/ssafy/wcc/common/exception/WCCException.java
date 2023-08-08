package com.ssafy.wcc.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WCCException extends RuntimeException {
    private final Error error;
}
