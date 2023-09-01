package com.ssafy.game.game.api.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GameOrderResponse {
    private int gameStatus;
    private List<String> order;

    public GameOrderResponse(int gameStatus, List<String> order) {
        this.gameStatus = gameStatus;
        this.order = order;
    }
}
