package com.ssafy.wcc.domain.topic.presentation;

import com.ssafy.wcc.common.aop.auth.Authorization;
import com.ssafy.wcc.domain.topic.application.dto.response.TopicTypeDetailResponse;
import com.ssafy.wcc.domain.topic.application.service.TopicService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

@Slf4j
@Api(tags = "Topic 컨트롤러")
@RestController
@RequestMapping("/topic")
@RequiredArgsConstructor
public class TopicController {

    Logger logger = LoggerFactory.getLogger(TopicController.class);

    private final TopicService topicService;

    @GetMapping("")
    @ApiOperation("게임 topic 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 404, message = "조회 실패"),
    })
    public ResponseEntity<?> getListTopic(
            @Authorization @ApiIgnore Long id
    ) {
        Map<String, Object> res = new HashMap<>();

        List<Object> list = topicService.getTopicResponse();
        res.put("isSuccess", true);
        res.put("data",list);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
