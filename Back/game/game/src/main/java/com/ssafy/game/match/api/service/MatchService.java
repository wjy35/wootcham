package com.ssafy.game.match.api.service;

import com.ssafy.game.match.api.request.MatchMember;
import com.ssafy.game.match.common.GameSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService {
    private final Deque<MatchMember> matchMemberQueue;
    private final Map<String,MatchMember> matchMemberSession;
    private final SimpMessageSendingOperations template;
    private final String READY_MATCH_MESSAGE="ready";
    private final String READY_MATCH_DESTINATION="/queue/match";

    @Autowired
    public MatchService(SimpMessageSendingOperations template) {
        this.matchMemberSession = new HashMap<>();
        this.matchMemberQueue = new ArrayDeque<>();
        this.template = template;
    }

    public void createMatchMemberBySessionId(String sessionId){
        MatchMember matchMember = new MatchMember(sessionId);

        this.matchMemberQueue.offer(matchMember);
        this.matchMemberSession.put(matchMember.getSessionId(),matchMember);
    }

    public void startMatch(){
        if(matchMemberQueue.size()< GameSetting.MAX_GAMEMEMBER_COUNT) return;

        try{
            Optional<Deque<MatchMember>> gameMember = Optional.of(readyGame());
            sendReadyToAllGameMember(gameMember.get());
        }catch (NullPointerException e){
            System.out.println("Match Fail");
        }

    }

    public synchronized Deque<MatchMember> readyGame(){
        System.out.println("Match Ready");
        MatchMember matchMember;
        Deque<MatchMember> gameMemberQueue = new ArrayDeque<>();

        while(!matchMemberQueue.isEmpty() && gameMemberQueue.size()< GameSetting.MAX_GAMEMEMBER_COUNT){
            matchMember = matchMemberQueue.poll();

            if(matchMember.isConnected()){
                gameMemberQueue.offer(matchMember);
            }
        }

        if(gameMemberQueue.size() == GameSetting.MAX_GAMEMEMBER_COUNT){
            System.out.println("Match Success");
            return gameMemberQueue;
        }

        while(!gameMemberQueue.isEmpty()){
            matchMember = gameMemberQueue.pollFirst();
            if(matchMember.isConnected()){
                matchMemberQueue.offerFirst(matchMember);
            }
        }

        return null;
    }

    private void sendReadyToAllGameMember(Deque<MatchMember> gameMemberQueue){
        System.out.println("Send MatchReady Message Start");

        System.out.println(gameMemberQueue);
        while(!gameMemberQueue.isEmpty()){
            sendReadyToGameMember(gameMemberQueue.poll());
        }

        System.out.println("Send MatchReady Message End");
    }

    private void sendReadyToGameMember(MatchMember gameMember){
        SimpMessageHeaderAccessor headerAccessor =  SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setLeaveMutable(true);
        headerAccessor.setSessionId(gameMember.getSessionId());

        System.out.println("To : " + gameMember.getSessionId());
        template.convertAndSendToUser(gameMember.getSessionId(),READY_MATCH_DESTINATION,READY_MATCH_MESSAGE,headerAccessor.getMessageHeaders());
    }

}
