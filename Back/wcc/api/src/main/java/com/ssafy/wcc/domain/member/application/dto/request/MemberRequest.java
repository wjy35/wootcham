package com.ssafy.wcc.domain.member.application.dto.request;

import lombok.Getter;

import javax.validation.constraints.Email;

@Getter
public class MemberRequest {

    @Email
    private String email;

    private String password;

    private String nickname;

}
