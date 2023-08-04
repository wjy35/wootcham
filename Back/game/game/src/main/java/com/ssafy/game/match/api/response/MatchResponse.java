package com.ssafy.game.match.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MatchResponse {
    private boolean isSuccess;

    private String memberId;
    private String groupId;
    private int matchStatus;

    public MatchResponse(String memberId, String groupId, int matchStatus) {
        this.memberId = memberId;
        this.groupId = groupId;
        this.matchStatus = matchStatus;
        this.isSuccess = true;
    }
}
