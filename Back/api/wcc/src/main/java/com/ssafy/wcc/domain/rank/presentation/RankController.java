package com.ssafy.wcc.domain.rank.presentation;

import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberInfoResponse;
import com.ssafy.wcc.domain.notice.presentation.NoticeController;
import com.ssafy.wcc.domain.rank.application.service.RankService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = "Rank 컨트롤러")
@RestController
@RequestMapping("/rank")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RankController {

    Logger logger = LoggerFactory.getLogger(NoticeController.class);

    private final RankService rankService;

    @GetMapping
    @ApiOperation(value = "랭커 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 404, message = "조회 실패"),
    })
    public ResponseEntity<Map<String, Object>> rank() {
        log.info("rank controller 진입");

        Map<String, Object> res = new HashMap<>();

        List<MemberInfoResponse> rankList = rankService.getRank();
        res.put("isSuccess", true);
        res.put("data", rankList);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }
}
