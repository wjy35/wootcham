package com.ssafy.game.match.api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.game.game.api.processor.GameProcessor;
import com.ssafy.game.game.db.entity.Game;
import com.ssafy.game.game.db.repository.GameRepository;
import com.ssafy.game.match.api.request.Member;
import com.ssafy.game.match.api.response.MatchResponse;
import com.ssafy.game.match.common.GameSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MatchService {
    private final String MATCH_DESTINATION ="/queue/match";
    private final int GAME_ENTER_REQUEST_WAIT_SECOND = 10;
    private final Deque<Member> matchMemberQueue;
    private final Map<String, Member> matchMemberSession;
    private final SimpMessageSendingOperations template;
    private final ObjectMapper mapper;
    private final GameRepository gameRepository;

    @Autowired
    public MatchService(SimpMessageSendingOperations template, GameRepository gameRepository) {
        this.gameRepository = gameRepository;
        this.matchMemberSession = new HashMap<>();
        this.matchMemberQueue = new ArrayDeque<>();
        this.template = template;
        this.mapper = new ObjectMapper();
    }

    public void createMatchMemberBySessionId(String sessionId){
        Member matchMember = new Member(sessionId);

        this.matchMemberQueue.offer(matchMember);
        this.matchMemberSession.put(matchMember.getSessionId(),matchMember);
    }

    public void deleteMatchMemberBySessionId(String sessionId){
        this.matchMemberSession.get(sessionId).disconnect();
        this.matchMemberSession.remove(sessionId);
    }

    public void startMatch(){
        if(matchMemberQueue.size()<GameSetting.MAX_GAMEMEMBER_COUNT) return;

        try{
            Optional<Deque<Member>> gameMember = Optional.of(getGameMembers());
            Game game = gameRepository.createNewGame();

            sendEnterRequestToAllGameMember(gameMember.get(),game);

            Thread.sleep(GAME_ENTER_REQUEST_WAIT_SECOND *1000);
            if(game.getMembers().size() == GameSetting.MAX_GAMEMEMBER_COUNT){
                sendEnterResult(game.getGameId());
                GameProcessor gameProcessor = new GameProcessor(game,this.template);
                Thread thread = new Thread(gameProcessor);
                thread.start();
            }else{
                gameRepository.deleteGameByGameId(game.getGameId());
                System.out.println("Game Deleted");
                /**
                 * ToDo Game 파괴시 현재 유저를 다시 매칭큐의 앞으로 이동시켜 주어야 함
                 */
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public synchronized Deque<Member> getGameMembers(){
        Member matchMember;
        Deque<Member> gameMemberQueue = new ArrayDeque<>();

        while(!matchMemberQueue.isEmpty() && gameMemberQueue.size()<GameSetting.MAX_GAMEMEMBER_COUNT){
            matchMember = matchMemberQueue.poll();

            if(matchMember.isConnected()){
                gameMemberQueue.offer(matchMember);
            }
        }

        if(gameMemberQueue.size() == GameSetting.MAX_GAMEMEMBER_COUNT){
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

    public void enterGame(String sessionId,String gameId){
        gameRepository.findGameByGameId(gameId).getMembers().put(sessionId,matchMemberSession.get(sessionId));
    }

    private void sendEnterRequestToAllGameMember(Deque<Member> gameMemberQueue, Game game){
        System.out.println("Start Send Enter Request");

        while(!gameMemberQueue.isEmpty()){
            sendEnterRequestToGameMember(gameMemberQueue.poll(),game.getGameId());
        }

        System.out.println("End Send Enter Request");
    }

    private void sendEnterRequestToGameMember(Member gameMember, String gameId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setLeaveMutable(true);
        headerAccessor.setSessionId(gameMember.getSessionId());

        try {
            String message = mapper.writeValueAsString(new MatchResponse(gameMember.getSessionId(),gameId));

            System.out.println("To : " + gameMember.getSessionId());
            System.out.println("Message = " + message);

            template.convertAndSendToUser(
                    gameMember.getSessionId(),
                    MATCH_DESTINATION,
                    message,
                    headerAccessor.getMessageHeaders()
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendEnterResult(String gameId){
        System.out.println("Send Load Game Request");
        template.convertAndSend( "/topic/enter/"+gameId,"true");
    }
}
