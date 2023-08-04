package com.ssafy.game.match.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchResponse {
    private boolean isSuccess;

    private String sessionId;
    private String gameId;
    private int matchStatus;

    public MatchResponse(String sessionId, String gameId, int matchStatus) {
        this.sessionId = sessionId;
        this.gameId = gameId;
        this.matchStatus = matchStatus;
        this.isSuccess = true;
    }
}
