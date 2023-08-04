package com.ssafy.game.game.api.controller;

import com.ssafy.game.game.api.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class GameController {

    private final GameService gameService;

    @SubscribeMapping("/topic/game/{sessionId}")
    void load(@DestinationVariable String sessionId, @Header("simpSessionId") String memberId ){
        gameService.load(sessionId,memberId);
    }
}
