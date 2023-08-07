package com.ssafy.game.game.api.service;

import com.ssafy.game.game.db.entity.GameMember;
import com.ssafy.game.game.db.repository.GameMemberRepository;
import com.ssafy.game.game.db.repository.GameSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameSessionRepository gameSessionRepository;
    private final GameMemberRepository gameMemberRepository;

    @Transactional
    public void load(String sessionId, String memberId){
        GameMember gameMember = new GameMember(sessionId,memberId);
        gameMemberRepository.save(gameMember);
        gameSessionRepository.findBySessionId(sessionId).getGameMembers().put(memberId,gameMember);
    }
}
