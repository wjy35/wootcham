package com.ssafy.wcc.domain.record.presentation;

import com.ssafy.wcc.common.aop.auth.Authorization;
import com.ssafy.wcc.domain.jwt.application.service.TokenService;
import com.ssafy.wcc.domain.record.application.dto.response.RecordResponse;
import com.ssafy.wcc.domain.record.application.service.RecordService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = "Record 컨트롤러")
@RestController
@RequestMapping("/record")
@RequiredArgsConstructor
public class RecordController {


    private final RecordService recordService;

    @GetMapping()
    @ApiOperation("전적 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "전적 조회 성공"),
            @ApiResponse(code = 404, message = "전적 조회 실패"),
    })
    public ResponseEntity<?> recordList(
            @Authorization @ApiIgnore Long id
    ) {
        Map<String, Object> res = new HashMap<>();
        List<RecordResponse> recordResponseList = recordService.getRecord(id);
        res.put("isSuccess", true);
        res.put("data", recordResponseList);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
