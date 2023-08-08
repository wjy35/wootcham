package com.ssafy.game.game.api.controller;

import com.ssafy.game.game.api.request.TopicRequest;
import com.ssafy.game.game.api.service.GameService;
import com.ssafy.game.game.db.entity.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class GameController {

    private final GameService gameService;

    @SubscribeMapping("/topic/game/{sessionId}")
    void load(@DestinationVariable String sessionId, @Header("simpSessionId") String memberId){
        gameService.load(sessionId,memberId);
    }

    @MessageMapping("/pick/shuffle/{sessionId}")
    void pick(@Payload TopicRequest topicRequest, @DestinationVariable String sessionId, @Header("simpSessionId") String memberId){
        gameService.pick(sessionId,memberId,topicRequest);
    }



}
