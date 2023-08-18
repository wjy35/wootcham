package com.ssafy.game.match.api.response;

import com.ssafy.game.match.common.MatchStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameSessionResponse {
    private boolean isSuccess;
    private String sessionId;
    private String memberToken;
    private String memberId;
    private int matchStatus;

    public GameSessionResponse(String sessionId, String memberToken, String memberId) {
        this.sessionId = sessionId;
        this.memberToken = memberToken;
        this.memberId = memberId;
        this.matchStatus = MatchStatus.CREATED;
        this.isSuccess = true;
    }
}
