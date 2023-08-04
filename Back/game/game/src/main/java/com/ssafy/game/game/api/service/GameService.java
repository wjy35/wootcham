package com.ssafy.game.game.api.service;

import com.ssafy.game.game.db.entity.GameMember;
import com.ssafy.game.game.db.repository.GameSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameSessionRepository gameSessionRepository;

    public void load(String sessionId, String memberId){
        gameSessionRepository.findGameSessionBySessionId(sessionId).getGameMembers().put(memberId,new GameMember(memberId));
    }
}
