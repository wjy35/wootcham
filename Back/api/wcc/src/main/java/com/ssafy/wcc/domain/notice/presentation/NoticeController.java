package com.ssafy.wcc.domain.notice.presentation;

import com.ssafy.wcc.common.exception.WCCException;
import com.ssafy.wcc.domain.jwt.application.service.TokenService;
import com.ssafy.wcc.domain.notice.application.dto.resonse.NoticeForAdminResponse;
import com.ssafy.wcc.domain.notice.application.dto.resonse.NoticeForUserResponse;
import com.ssafy.wcc.domain.notice.application.service.NoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    private final TokenService tokenService;
    private final NoticeService noticeService;

    @GetMapping
    @ApiOperation("최근 공지 4개 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 조회 성공"),
            @ApiResponse(code = 404, message = "공지사항 조회 실패"),
    })
    public ResponseEntity<?> listNoticeForUsers(@RequestHeader("access_token") String accessToken) {
        logger.info("listNoticeForUsers controller 진입");
        Map<String, Object> res = new HashMap<>();
        try {
            List<NoticeForUserResponse> list = noticeService.getNoticeListForUsers(Long.parseLong(tokenService.getAccessTokenId(accessToken)));
            res.put("isSuccess", true);
            res.put("data", list);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (WCCException e) {
            res.put("isSuccess", false);
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/list")
    @ApiOperation("관리자. 공지 목록 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공지사항 조회 성공"),
            @ApiResponse(code = 404, message = "공지사항 조회 실패"),
    })
    public ResponseEntity<?> listNoticeForAdmin(@RequestHeader("access_token") String accessToken) {
        log.info("listNoticeForAdmin controller 진입");
        Map<String, Object> res = new HashMap<>();
        try {
            List<NoticeForAdminResponse> list = noticeService.getNoticeListForAdmin(Long.parseLong(tokenService.getAccessTokenId(accessToken)));
            res.put("isSuccess", true);
            res.put("data", list);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (WCCException e) {
            res.put("isSuccess", false);
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
    }

}
