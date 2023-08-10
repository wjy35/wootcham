package com.ssafy.wcc.domain.jwt.presentation;

import com.ssafy.wcc.domain.jwt.application.service.TokenService;
import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.service.MemberServiceImpl;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Api(tags = "Jwt 컨트롤러")
@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin("*")
public class JwtController {

    Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    private final TokenService tokenService;

    @PostMapping("/refresh")
    @ApiOperation(value = "토큰 갱신")
    @ApiResponses({
            @ApiResponse(code = 200, message = "갱신 성공"),
            @ApiResponse(code = 404, message = "갱신 실패")
    })
    public ResponseEntity<Map<String, Object>> refreshToken(@RequestHeader("refresh_token") @ApiParam(value = "refresh_token", required = true) String refreshToken) {
        logger.info("refreshToken service 진입");
        Map<String, Object> res = new HashMap<>();
        if (tokenService.checkToken(refreshToken)) {
            if (tokenService.getRefreshTokenId(refreshToken) != null) {
                String id = tokenService.getRefreshTokenId(refreshToken);
                String newAccessToken = tokenService.createAccessToken(id);
                res.put("isSuccess", true);
                res.put("access_token", newAccessToken);
                return new ResponseEntity<>(res, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
