package com.ssafy.game.match.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimerResponse {
    private boolean isSuccess;
    private int second;
    private int matchStatus;

    public TimerResponse(int second) {
        this.isSuccess = true;
        this.second = second;
        this.matchStatus = MatchStatus.MATCHED;
    }
}
