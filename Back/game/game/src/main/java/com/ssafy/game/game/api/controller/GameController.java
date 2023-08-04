package com.ssafy.game.game.api.controller;

import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;

@Controller
@CrossOrigin(originPatterns = "*")
public class GameController {

    @SubscribeMapping("/topic/game/{gameId}")
    void load(){
        System.out.println("load game");
    }

}
