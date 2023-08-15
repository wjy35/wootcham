package com.ssafy.wcc.domain.collection.presentation;

import com.ssafy.wcc.domain.collection.application.dto.response.CollectionResponse;
import com.ssafy.wcc.domain.collection.application.service.CollectionItemService;
import com.ssafy.wcc.domain.collection.db.entity.CollectionItem;
import com.ssafy.wcc.domain.jwt.application.service.TokenService;
import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.service.MemberItemService;
import com.ssafy.wcc.domain.member.db.entity.MemberItem;
import com.ssafy.wcc.domain.notice.presentation.NoticeController;
import io.netty.handler.codec.json.JsonObjectDecoder;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Api(tags = "Collection 컨트롤러")
@RestController
@RequestMapping("/collection")
@RequiredArgsConstructor
public class CollectionController {

    Logger logger = LoggerFactory.getLogger(NoticeController.class);

    private final TokenService tokenService;

    private final CollectionItemService collectionItemService;

    private String id;

    @GetMapping
    @ApiOperation(value = "도감 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 404, message = "조회 실패"),
    })
    public ResponseEntity<Map<String, Object>> collectionList(@RequestHeader("Authorization") @ApiParam(value = "Authorization", required = true) String accessToken) {
        logger.info("collectionList controller 진입");

        Map<String, Object> res = new HashMap<>();
        id = tokenService.getIdByToken(accessToken);

        List<CollectionResponse> list = collectionItemService.getCollectionList(Long.parseLong(id));

        res.put("isSuccess", true);
        res.put("data", list);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation(value = "도감 아이템 구매")
    @ApiResponses({
            @ApiResponse(code = 200, message = "구매 성공"),
            @ApiResponse(code = 400, message = "구매하지 않은 아이템"),
            @ApiResponse(code = 404, message = "구매 실패"),
    })
    public ResponseEntity<Map<String, Object>> buy(@RequestBody Map<String, Integer> collectionId, @RequestHeader("Authorization") @ApiParam(value = "Authorization", required = true) String accessToken) {
        logger.info("buy controller 진입");

        Map<String, Object> res = new HashMap<>();

        id = tokenService.getIdByToken(accessToken);

        collectionItemService.buy(Long.parseLong(id), collectionId.get("collection_id"));
        res.put("isSuccess", true);
        return new ResponseEntity<>(res, HttpStatus.OK);

    }

    @PutMapping()
    @ApiOperation(value = "도감 아이템 착용")
    @ApiResponses({
            @ApiResponse(code = 200, message = "착용 성공"),
            @ApiResponse(code = 404, message = "착용 실패"),
    })
    public ResponseEntity<Map<String, Object>> wear(@RequestBody Map<String, Integer> collectionId, @RequestHeader("Authorization") @ApiParam(value = "Authorization", required = true) String accessToken) {
        logger.info("wear controller 진입");

        Map<String, Object> res = new HashMap<>();

        id = tokenService.getIdByToken(accessToken);

        collectionItemService.wear(Long.parseLong(id), collectionId.get("collection_id"));
        res.put("isSuccess", true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
