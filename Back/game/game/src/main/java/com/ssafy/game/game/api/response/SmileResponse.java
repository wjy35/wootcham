package com.ssafy.game.game.api.response;

public class SmileResponse {
    private int gameStatus;
    private String memberToken;

    public SmileResponse(String memberToken) {
        this.gameStatus = GameStatus.SMILE;
        this.memberToken = memberToken;
    }
}
