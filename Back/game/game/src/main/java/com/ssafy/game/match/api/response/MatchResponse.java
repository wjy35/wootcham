package com.ssafy.game.match.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchResponse {
    private boolean isSuccess;
    private String sessionId;
    private String gameId;

    public MatchResponse(String sessionId, String gameId) {
        this.sessionId = sessionId;
        this.gameId = gameId;
        this.isSuccess = true;
    }
}
