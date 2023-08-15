package com.ssafy.game.game.api.response;

import lombok.Data;

import java.util.List;

@Data
public class PresentSettingResponse {
    private int gameStatus;
    private List<String> order;

    public PresentSettingResponse(List<String> order) {
        this.gameStatus = GameStatus.PRESNET_SETTING;
        this.order = order;
    }
}
