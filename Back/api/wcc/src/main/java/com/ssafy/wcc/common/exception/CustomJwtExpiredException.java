package com.ssafy.wcc.common.exception;

public class CustomJwtExpiredException extends RuntimeException{

    public CustomJwtExpiredException(String msg){
        super(msg);
    }
}
