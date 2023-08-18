package com.ssafy.game.game.db.repository;

import com.ssafy.game.game.db.entity.GameSession;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GameSessionRepository {
    private Map<String, GameSession> sessions;

    public GameSessionRepository() {
        this.sessions = new HashMap<>();
    }

    public void insertGameSession(GameSession gameSession){
        sessions.put(gameSession.getSessionId(),gameSession);
    }

    public GameSession findBySessionId(String sessionId){
        return sessions.get(sessionId);
    }

}
