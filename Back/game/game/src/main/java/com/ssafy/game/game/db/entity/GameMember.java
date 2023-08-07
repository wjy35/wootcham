package com.ssafy.game.game.db.entity;

import lombok.Getter;

@Getter
public class GameMember {
    private String memberId;
    private String sessionId;
    boolean connected;

    public GameMember(String sessionId, String memberId) {
        this.sessionId = sessionId;
        this.memberId = memberId;
        this.connected = true;
    }
}
