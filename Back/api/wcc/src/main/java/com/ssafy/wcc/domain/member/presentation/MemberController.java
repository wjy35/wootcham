package com.ssafy.wcc.domain.member.presentation;


import com.ssafy.wcc.domain.collection.application.service.CollectionItemService;
import com.ssafy.wcc.domain.member.application.dto.request.EmailVerifyRequest;
import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberInfoResponse;
import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;
import com.ssafy.wcc.domain.member.application.service.EmailService;
import com.ssafy.wcc.domain.member.application.service.MemberService;
import com.ssafy.wcc.domain.jwt.application.service.TokenService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "Member 컨트롤러")
@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final TokenService tokenService;

    private final MemberService memberService;

    private final EmailService emailService;

    private final CollectionItemService collectionItemService;

    @PostMapping("/join")
    @ApiOperation(value = "회원 가입")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 성공"),
            @ApiResponse(code = 404, message = "회원가입 실패"),
    })
    public ResponseEntity<?> signUp(
            @RequestBody @ApiParam(value = "회원가입 정보", required = true) MemberRequest signupInfo
    ) {
        Map<String, Object> resultMap = new HashMap<>();
        memberService.memberSignUp(signupInfo);
        resultMap.put("isSuccess", true);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/{email}")
    @ApiOperation(value = "email 인증 중복 검사 및 인증번호 전송")
    @ApiResponses({
            @ApiResponse(code = 200, message = "이메일 인증 메일 전송 성공"),
            @ApiResponse(code = 404, message = "사용 불가능한 이메일"),
    })
    public ResponseEntity<?> confirmEmail(@PathVariable String email) throws MessagingException, UnsupportedEncodingException {
        Map<String, Object> resultMap = new HashMap<>();

        // 이메일 중복 검사
        if (memberService.checkEmail(email)) return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        // 인증 메일 전송
        try {
            emailService.sendMessage(email);
        } catch (IllegalArgumentException e) {
            resultMap.put("isSuccess", false);
            return new ResponseEntity<>(resultMap, HttpStatus.NOT_FOUND);
        }

        resultMap.put("isSuccess", true);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/code")
    @ApiOperation(value = "email 인증 번호 확인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "이메일 인증 성공"),
            @ApiResponse(code = 404, message = "이메일 인증 실패"),
    })
    public ResponseEntity<?> verifyEmail(
            @RequestBody @ApiParam(value = "이메일 인증 정보", required = true) EmailVerifyRequest emailVerifyRequest
    ) {

        Map<String, Object> resultMap = new HashMap<>();

        try {
            emailService.verifyEmail(emailVerifyRequest);
        } catch (ChangeSetPersister.NotFoundException e) {
            resultMap.put("isSuccess", false);
            return new ResponseEntity<>(resultMap, HttpStatus.NOT_FOUND);
        }
        resultMap.put("isSuccess", false);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/login")
    @ApiOperation(value = "로그인")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그인 성공"),
            @ApiResponse(code = 401, message = "잘못된 비밀번호"),
            @ApiResponse(code = 404, message = "존재하지 않는 사용자")
    })
    public ResponseEntity<Map<String, Object>> login(
            @RequestBody @ApiParam(value = "로그인 정보") MemberRequest loginInfo
    ) {
        Map<String, Object> res = new HashMap<>();

        try {
            long id = memberService.memberLogin(loginInfo);
            MemberLoginResponse token = tokenService.makeMemberLoginResponse(String.valueOf(id));
            res.put("isSuccess", true);
            res.put("access-token", token.getAccess_token());
            res.put("refresh-token", token.getRefresh_token());

            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/logout")
    @ApiOperation(value = "로그아웃")
    @ApiResponses({
            @ApiResponse(code = 200, message = "로그아웃 성공"),
            @ApiResponse(code = 404, message = "로그아웃 실패")
    })
    public ResponseEntity<Map<String, Object>> logout(HttpServletRequest req) {
        Map<String, Object> res = new HashMap<>();
        try{
            String accessToken = req.getHeader("access-token");
            String refreshToken = req.getHeader("refresh-token");
            tokenService.saveLogoutToken(accessToken);
            tokenService.deleteRefreshToken(refreshToken);
            res.put("isSuccess", true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    @ApiOperation(value = "회원정보 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 404, message = "조회 실패")
    })
    public ResponseEntity<Map<String, Object>> memberInfo(HttpServletRequest req) {
        Map<String, Object> res = new HashMap<>();
        String accessToken = req.getHeader("access-token");

        try{
            MemberInfoResponse memberInfoResponse = memberService.memberInfoResponse(Long.parseLong(tokenService.getAccessTokenId(accessToken)));
            res.put("isSuccess", true);
            res.put("data", memberInfoResponse);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    @ApiOperation(value = "회원정보 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "수정 성공"),
            @ApiResponse(code = 404, message = "수정 실패")
    })
    public ResponseEntity<Map<String, Object>> memberUpdate(@RequestBody MemberRequest memberRequest, HttpServletRequest req) {
        Map<String, Object> res = new HashMap<>();
        String accessToken = req.getHeader("access-token");
        String id = tokenService.getAccessTokenId(accessToken);
        try{
            memberService.memberUpdate(memberRequest, id);
            res.put("isSuccess", true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping()
    @ApiOperation(value = "회원 탈퇴")
    @ApiResponses({
            @ApiResponse(code = 200, message = "탈퇴 성공"),
            @ApiResponse(code = 404, message = "탈퇴 실패")
    })
    public ResponseEntity<Map<String, Object>> memberDelete(HttpServletRequest req) {
        Map<String, Object> res = new HashMap<>();
        String accessToken = req.getHeader("access-token");
        String id = tokenService.getAccessTokenId(accessToken);
        try{
            memberService.memberDelete(id);
            res.put("isSuccess", true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/nickname")
    @ApiOperation(value="닉네임 중복 검사")
    @ApiResponses({
            @ApiResponse(code = 200, message = "중복 아님"),
            @ApiResponse(code = 404, message = "중복")
    })
    public ResponseEntity<Map<String, Object>> nickNameCheck(@RequestBody MemberRequest loginInfo)
    {
        Map<String, Object> res = new HashMap<>();

        String nickName = loginInfo.getNickname();

        if(memberService.checkNickname(nickName)) {
            res.put("unique", true);
            return new ResponseEntity<>(res, HttpStatus.OK);
        }else{
            res.put("unique", false);
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }

}
