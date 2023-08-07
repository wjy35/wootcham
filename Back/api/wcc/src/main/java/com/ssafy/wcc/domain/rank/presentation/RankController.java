package com.ssafy.wcc.domain.rank.presentation;

import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberInfoResponse;
import com.ssafy.wcc.domain.rank.application.service.RankService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(tags = "Rank 컨트롤러")
@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RankController {
    private final RankService rankService;

    @PostMapping()
    @ApiOperation(value="랭커 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 404, message = "조회 실패"),
    })
    public ResponseEntity<Map<String, Object>> rank( @RequestBody @ApiParam(value = "회원가입 정보", required = true) MemberRequest signupInfo) {
        Map<String, Object> res = new HashMap<>();
        try {
            List<MemberInfoResponse> rankList= rankService.getRank();
            res.put("isSuccess",true);
            res.put("data", rankList);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}
