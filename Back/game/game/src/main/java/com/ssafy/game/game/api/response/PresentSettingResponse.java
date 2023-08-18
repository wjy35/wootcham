package com.ssafy.game.game.api.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PresentSettingResponse {
    private int gameStatus;
    private List<String> order;

    public PresentSettingResponse(List<String> order) {
        this.gameStatus = GameStatus.PRESNET_SETTING;
        this.order = order;
    }
}
