package com.ssafy.game.game.api.service;

import com.ssafy.game.game.api.request.LoadRequest;
import com.ssafy.game.game.api.request.TopicRequest;
import com.ssafy.game.game.db.entity.GameMember;
import com.ssafy.game.game.db.repository.GameMemberRepository;
import com.ssafy.game.game.db.repository.GameSessionRepository;
import com.ssafy.game.util.MessageSender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class GameService {
    private final GameSessionRepository gameSessionRepository;
    private final GameMemberRepository gameMemberRepository;
    private final MessageSender sender;

    @Transactional
    public void load(String sessionId, String memberId){
        GameMember gameMember = gameMemberRepository.findByMemberId(memberId);
        gameSessionRepository.findBySessionId(sessionId).getGameMembers().put(gameMember.getMemberToken(), gameMember);
    }

    public void pick(String sessionId, TopicRequest topicRequest){
        gameSessionRepository.findBySessionId(sessionId).pickTopic(topicRequest.getMemberToken(), topicRequest.getType(), topicRequest.getKeyword());
    }

    public void skipPreparePresent(String sessionId){
        gameSessionRepository.findBySessionId(sessionId).setCheckedSkipPreparedPresent(true);
    }
}
