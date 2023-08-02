package com.ssafy.wcc.domain.member.application.dto.request;

import lombok.Getter;

@Getter
public class EmailVerifyRequest {
    private String email;
    private String code;
}
