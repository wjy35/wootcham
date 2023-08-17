package com.ssafy.game.game.db.entity;

import lombok.Getter;

@Getter
public class GameMember {
    private String sessionId;
    private String screenSessionId;
    private String memberId;
    private String memberToken;
    private String nickname;
    boolean connected;

    public GameMember(String sessionId, String memberId, String memberToken, String nickname, String screenSessionId) {
        this.sessionId = sessionId;
        this.screenSessionId = screenSessionId;
        this.memberId = memberId;
        this.memberToken = memberToken;
        this.nickname = nickname;
        this.connected = true;
    }
}
