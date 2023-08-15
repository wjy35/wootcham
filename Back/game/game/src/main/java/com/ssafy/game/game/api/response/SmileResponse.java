package com.ssafy.game.game.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SmileResponse {
    private int gameStatus;
    private String memberToken;

    public SmileResponse(String memberToken) {
        this.gameStatus = GameStatus.SMILE;
        this.memberToken = memberToken;
    }
}
