package com.ssafy.game.game.api.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoundSettingResponse {
    private int gameStatus;
    private List<String> order;
    private int round;

    public RoundSettingResponse(List<String> order,int round) {
        this.gameStatus = GameStatus.ROUND_SETTING;
        this.order = order;
        this.round = round;
    }
}
