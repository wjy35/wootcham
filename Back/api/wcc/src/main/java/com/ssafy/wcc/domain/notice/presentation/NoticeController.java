package com.ssafy.wcc.domain.notice.presentation;

import com.ssafy.wcc.common.aop.auth.Authorization;
import com.ssafy.wcc.domain.jwt.application.service.TokenService;
import com.ssafy.wcc.domain.notice.application.dto.request.NoticeRequest;
import com.ssafy.wcc.domain.notice.application.dto.response.NoticeForAdminResponse;
import com.ssafy.wcc.domain.notice.application.dto.response.NoticeResponse;
import com.ssafy.wcc.domain.notice.application.service.NoticeService;
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
@Api(tags = "Notice 컨트롤러")
@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
public class NoticeController {

    Logger logger = LoggerFactory.getLogger(NoticeController.class);

    private final NoticeService noticeService;

    @GetMapping
    @ApiOperation("최근 공지 4개 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 조회 성공"),
            @ApiResponse(code = 404, message = "공지사항 조회 실패"),
    })
    public ResponseEntity<?> listNoticeForUsers(
            @Authorization @ApiIgnore Long id
    ) {
        logger.info("listNoticeForUsers controller 진입");
        Map<String, Object> res = new HashMap<>();

        List<NoticeResponse> list = noticeService.getNoticeListForUsers(id);
        res.put("isSuccess", true);
        res.put("data", list);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/list")
    @ApiOperation("관리자. 공지 목록 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 조회 성공"),
            @ApiResponse(code = 403, message = "권한이 없는 사용자"),
            @ApiResponse(code = 404, message = "공지사항 조회 실패"),
    })
    public ResponseEntity<?> listNoticeForAdmin(
            @Authorization @ApiIgnore Long id
    ) {
        log.info("listNoticeForAdmin controller 진입");
        Map<String, Object> res = new HashMap<>();

        List<NoticeForAdminResponse> list = noticeService.getNoticeListForAdmin(id);
        res.put("isSuccess", true);
        res.put("data", list);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("/detail")
    @ApiOperation("관리자. 공지 상세보기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 상세보기 성공"),
            @ApiResponse(code = 403, message = "권한이 없는 사용자"),
            @ApiResponse(code = 404, message = "공지사항 상세보기 실패"),
    })
    public ResponseEntity<?> noticeDetail(
            @Authorization @ApiIgnore Long id,
            @RequestBody @ApiParam(value = "글 아이디", required = true) NoticeRequest request
    ) {
        Map<String, Object> res = new HashMap<>();

        NoticeResponse notice = noticeService.getNoticeDetail(id, request.getId());
        res.put("isSuccess", true);
        res.put("data", notice);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation("관리자. 공지사항 등록하기")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 상세보기 성공"),
            @ApiResponse(code = 403, message = "권한이 없는 사용자"),
            @ApiResponse(code = 404, message = "공지사항 상세보기 실패"),
    })
    public ResponseEntity<?> registerNotice(
            @Authorization @ApiIgnore Long id,
            @RequestBody @ApiParam(value = "글 정보", required = true) NoticeRequest request
    ) {
        Map<String, Object> res = new HashMap<>();

        noticeService.registerNotice(id, request);
        res.put("isSuccess", true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("")
    @ApiOperation("관리자. 공지사항 수정")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 수정 성공"),
            @ApiResponse(code = 403, message = "권한이 없는 사용자"),
            @ApiResponse(code = 404, message = "공지사항 수정 실패"),
    })
    public ResponseEntity<?> updateNotice(
            @Authorization @ApiIgnore Long id,
            @RequestBody @ApiParam(value = "글 정보", required = true) NoticeRequest request
    ) {
        Map<String, Object> res = new HashMap<>();

        noticeService.updateNotice(id, request);
        res.put("isSuccess", true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
