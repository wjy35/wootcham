package com.ssafy.game.match.api.response;

import com.ssafy.game.match.common.MatchStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LastTimeResponse {
    private boolean isSuccess;
    private int second;
    private int matchStatus;

    public LastTimeResponse(int second) {
        this.isSuccess = true;
        this.second = second;
        this.matchStatus = MatchStatus.MATCHED;
    }
}
