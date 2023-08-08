package com.ssafy.wcc.domain.jwt.presentation;

import com.ssafy.wcc.domain.jwt.application.service.TokenService;
import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@Api(tags = "Jwt 컨트롤러")
@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin("*")
public class JwtController {

    private final TokenService tokenService;

    @PostMapping("/refresh")
    @ApiOperation(value = "토큰 갱신")
    @ApiResponses({
            @ApiResponse(code = 200, message = "갱신 성공"),
            @ApiResponse(code = 404, message = "갱신 실패")
    })
    public ResponseEntity<Map<String, Object>> refreshToken(HttpServletRequest req) {
        Map<String, Object> res = new HashMap<>();
        String refreshToken = req.getHeader("refresh_token");
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
