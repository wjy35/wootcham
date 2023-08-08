package com.ssafy.game.game.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameStatusResponse {
    private int gameStatus;
    private int second;

    public GameStatusResponse(int gameStatus, int second) {
        this.gameStatus = gameStatus;
        this.second = second;
    }

}
