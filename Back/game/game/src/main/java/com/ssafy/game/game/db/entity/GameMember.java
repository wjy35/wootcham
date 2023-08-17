package com.ssafy.game.game.db.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameMember {
    private String sessionId;
    private String screenToken;
    private String memberId;
    private String memberToken;
    private String nickname;
    boolean connected;

    public GameMember(String sessionId, String memberId, String memberToken, String nickname, String screenToken) {
        this.sessionId = sessionId;
        this.screenToken = screenToken;
        this.memberId = memberId;
        this.memberToken = memberToken;
        this.nickname = nickname;
        this.connected = true;
    }
}
