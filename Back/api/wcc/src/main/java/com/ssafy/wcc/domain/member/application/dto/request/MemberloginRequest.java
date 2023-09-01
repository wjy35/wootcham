package com.ssafy.wcc.domain.member.application.dto.request;

import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class MemberloginRequest {
    @Email
    private String email;

    private String password;
}
