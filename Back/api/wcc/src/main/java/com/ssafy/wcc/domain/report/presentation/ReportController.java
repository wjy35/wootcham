package com.ssafy.wcc.domain.report.presentation;

import com.ssafy.wcc.domain.jwt.application.service.TokenService;
import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.report.application.dto.response.AllMemberResponse;
import com.ssafy.wcc.domain.report.application.service.ReportService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "Member 컨트롤러")
@RestController
@RequestMapping("/report")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ReportController {

    private final ReportService reportService;

    private final TokenService tokenService;

    @PostMapping()
    @ApiOperation(value = "해당 유저 신고")
    @ApiResponses({
            @ApiResponse(code = 200, message = "신고 성공"),
            @ApiResponse(code = 404, message = "신고 실패"),
    })
    public ResponseEntity<Map<String,Object>> report(@RequestBody Map<String,Integer> memberId, HttpServletRequest req) {
        Map<String, Object> resultMap = new HashMap<>();
        reportService.report(Long.valueOf(memberId.get("member_id")));
        resultMap.put("isSuccess", true);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }

    @PostMapping("/member")
    @ApiOperation(value = "전체 유저 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 404, message = "조회 실패"),
    })
    public ResponseEntity<Map<String,Object>> memberList(HttpServletRequest req) {
        Map<String, Object> resultMap = new HashMap<>();
        String accessToken = req.getHeader("access_token");
        String id = tokenService.getAccessTokenId(accessToken);
        List<AllMemberResponse> memberResponseList = reportService.getList();

        JSONArray arr = new JSONArray();

        for(int i=0; i<memberResponseList.size(); i++){
            JSONObject data = new JSONObject();
            AllMemberResponse memberResponse = memberResponseList.get(i);
            data.put("id",memberResponse.getId());
            data.put("email",memberResponse.getEmail());
            data.put("nickname",memberResponse.getNickname());
            data.put("point",memberResponse.getPoint());
            data.put("money",memberResponse.getMoney());
            data.put("admin",memberResponse.getAdmin());
            data.put("suspension_day",memberResponse.getSuspensionDay());
            data.put("report_count",memberResponse.getReportCount());
            arr.add(data);
        }
        resultMap.put("isSuccess", true);
        resultMap.put("data", arr);

        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
}
