package com.ssafy.game.game.db.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class GameSession {
    private String sessionId;
    private Map<String, GameMember> gameMembers;

    public GameSession(String sessionId) {
        this.sessionId = sessionId;
        this.gameMembers = new HashMap<>();
    }
}
