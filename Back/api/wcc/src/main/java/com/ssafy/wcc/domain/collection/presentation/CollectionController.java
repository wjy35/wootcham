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
@CrossOrigin("*")
public class CollectionController {

    Logger logger = LoggerFactory.getLogger(NoticeController.class);

    private final TokenService tokenService;

    private final CollectionItemService collectionItemService;

    @GetMapping
    @ApiOperation(value = "도감 조회")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 404, message = "조회 실패"),
    })
    public ResponseEntity<Map<String, Object>> collectionList(@RequestHeader("access_token") @ApiParam(value = "access_token", required = true) String accessToken) {
        logger.info("collectionList controller 진입");

        Map<String, Object> res = new HashMap<>();
        String id = tokenService.getAccessTokenId(accessToken);

        try {
            List<CollectionItem> list = collectionItemService.getCollectionList(Long.parseLong(id));

            JSONArray arr = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                JSONObject data = new JSONObject();
                CollectionItem collectionItem = list.get(i);
                data.put("id", collectionItem.getId());
                data.put("type", collectionItem.getType());
                data.put("name", collectionItem.getName());
                data.put("price", collectionItem.getPrice());
                data.put("description", collectionItem.getDescription());
                data.put("wear", false);
                data.put("buy", false);
                for (int j = 0; j < collectionItem.getMemberItems().size(); j++) {
                    if (collectionItem.getMemberItems().get(j).getMember().getId() == Long.parseLong(id)) {
                        if (collectionItem.getMemberItems().get(j).isWear()) {
                            data.put("wear", true);
                        }

                        if (collectionItem.getMemberItems().get(j).isBuy()) {
                            data.put("buy", true);
                        }
                        data.put("ssss", collectionItem.getMemberItems().get(j).getMember().getId());
                    }
                }

                arr.add(data);
            }
            res.put("isSuccess", true);
            res.put("data", arr);
            return new ResponseEntity<>(res, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping()
    @ApiOperation(value = "도감 아이템 구매")
    @ApiResponses({
            @ApiResponse(code = 200, message = "구매 성공"),
            @ApiResponse(code = 400, message = "구매하지 않은 아이템"),
            @ApiResponse(code = 404, message = "구매 실패"),
    })
    public ResponseEntity<Map<String, Object>> buy(@RequestBody Map<String, Integer> collectionId, @RequestHeader("access_token") @ApiParam(value = "access_token", required = true) String accessToken) {
        logger.info("buy controller 진입");

        Map<String, Object> res = new HashMap<>();

        String id = tokenService.getAccessTokenId(accessToken);

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
    public ResponseEntity<Map<String, Object>> wear(@RequestBody Map<String, Integer> collectionId, @RequestHeader("access_token") @ApiParam(value = "access_token", required = true) String accessToken) {
        logger.info("wear controller 진입");

        Map<String, Object> res = new HashMap<>();

        String id = tokenService.getAccessTokenId(accessToken);

        collectionItemService.wear(Long.parseLong(id), collectionId.get("collection_id"));
        res.put("isSuccess", true);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
