package com.ssafy.wcc.domain.member.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Getter
public class MemberRequest {

    @Email
    private String email;

    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*[\\W_]).{8,16}$")
    private String password;

    @Pattern(regexp = "^[a-zA-Z0-9가-힣]{1,10}$")
    private String nickname;

}
