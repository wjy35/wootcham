package com.ssafy.game.game.db.entity;

import com.ssafy.game.match.api.request.Member;

import java.util.HashMap;
import java.util.Map;

/**
 * ToDo
 *
 * 이 클래스는 Match를 위한 Game
 * Match가 아닌 GameSession Class 만들어야 함
 */
public class Game {
    private String gameId;
    private Map<String, Member> members;

    public String getGameId() {
        return gameId;
    }

    public Map<String, Member> getMembers() {
        return members;
    }

    public Game(String gameId) {
        this.gameId = gameId;
        this.members = new HashMap<>();
    }
}
