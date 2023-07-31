package com.ssafy.wcc.domain.member.presentation;


import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.service.EmailService;
import com.ssafy.wcc.domain.member.application.service.MemberService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "Member 컨트롤러")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final EmailService emailService;

    @PostMapping("/{email}")
    @ApiOperation(value = "email 인증 중복 검사 및 인증번호 전송")
    @ApiResponses({
            @ApiResponse(code = 200, message = "이메일 인증 메일 전송 성공"),
            @ApiResponse(code = 404, message = "사용 불가능한 이메일"),
    })
    public ResponseEntity<?> confirmEmail(@PathVariable String email) throws MessagingException, UnsupportedEncodingException {
        // 이메일 중복 검사
        if (memberService.checkEmail(email)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        // 인증 메일 전송
        try {
            emailService.sendMessage(email);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
