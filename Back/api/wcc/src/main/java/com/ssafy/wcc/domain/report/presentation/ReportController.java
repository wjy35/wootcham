package com.ssafy.wcc.domain.report.presentation;

import com.ssafy.wcc.domain.jwt.application.service.TokenService;
import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.report.application.service.ReportService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "Member 컨트롤러")
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReportController {

    private final ReportService reportService;

    private final TokenService tokenService;

    @PostMapping("")
    @ApiOperation(value = "해당 유저 신고")
    @ApiResponses({
            @ApiResponse(code = 200, message = "신고 성공"),
            @ApiResponse(code = 404, message = "신고 실패"),
    })
    public ResponseEntity<Map<String,Object>> memberList(@RequestBody Map<String,Integer> memberId, HttpServletRequest req) {
        Map<String, Object> resultMap = new HashMap<>();
        reportService.report(Long.valueOf(memberId.get("member_id")));
        resultMap.put("isSuccess", true);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
