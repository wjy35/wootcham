package com.ssafy.game.game.api.service;

import com.ssafy.game.game.api.request.*;
import com.ssafy.game.game.db.entity.GameMember;
import com.ssafy.game.game.db.entity.GameSession;
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

    @Transactional
    public void load(String sessionId, String memberId){
        GameMember gameMember = gameMemberRepository.findByMemberId(memberId);
        GameSession gameSession = gameSessionRepository.findBySessionId(sessionId);
        gameSession.getGameMembers().put(gameMember.getMemberToken(), gameMember);
        gameSession.loadTopic(gameMember.getMemberToken());
        gameSession.loadSmileCount(gameMember.getMemberToken());
    }

    public void pick(String sessionId, TopicRequest topicRequest){
        gameSessionRepository.findBySessionId(sessionId).pickTopic(topicRequest.getMemberToken(), topicRequest.getType(), topicRequest.getKeyword());
    }

    public void upSmileCount(UpSmileCountRequest upSmileCountRequest){
        gameSessionRepository.findBySessionId(upSmileCountRequest.getSessionId()).upSmileCount(upSmileCountRequest.getMemberToken());
    }

    public void skipPreparePresent(String sessionId, SkipPrepareRequest skipPrepareRequest) {
        GameSession gameSession = gameSessionRepository.findBySessionId(sessionId);

        System.out.println("skipPrepareRequest = " + skipPrepareRequest);
        System.out.println("gameSession.getOrderList().get(0) = " + gameSession.getOrderList().get(0));
        if(!gameSession.getOrderList().get(0).equals(skipPrepareRequest.getMemberToken())) return;

        gameSession.setCheckedSkipPreparedPresent(true);
        gameSession.commitTopic(
                skipPrepareRequest.getMemberToken(),
                skipPrepareRequest.getType(),
                skipPrepareRequest.getKeyword(),
                skipPrepareRequest.getUseTopic(),
                skipPrepareRequest.getDisplayTopic()
        );
    }

    public void skipPresent(String sessionId, String memberToken) {
        GameSession gameSession = gameSessionRepository.findBySessionId(sessionId);
        if(!gameSession.getOrderList().get(0).equals(memberToken)) return;

        gameSession.setCheckedSkipPresent(true);

    }

    public void skipPickTopic(SkipPickTopicRequest skipPickTopicRequest){
        gameSessionRepository
                .findBySessionId(skipPickTopicRequest.getSessionId())
                .addPickedGameMembers(skipPickTopicRequest.getMemberToken());
    }

    public void disconnect(String memberId){
        GameMember disconnectedGameMember = gameMemberRepository.findByMemberId(memberId);
        disconnectedGameMember.setConnected(false);
        gameSessionRepository.findBySessionId(disconnectedGameMember.getSessionId()).disconnect(disconnectedGameMember.getMemberToken());
    }
}
