package com.ssafy.game.match.api.service;

import com.ssafy.game.game.api.processor.GameProcessor;
import com.ssafy.game.game.db.entity.Game;
import com.ssafy.game.game.db.repository.GameRepository;
import com.ssafy.game.match.api.request.Member;
import com.ssafy.game.match.api.response.MatchResponse;
import com.ssafy.game.match.common.GameSetting;
import com.ssafy.game.match.db.repository.MatchMemberSession;
import com.ssafy.game.util.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MatchService {
    private final String MATCH_DESTINATION ="/queue/match";
    private final int GAME_ENTER_REQUEST_WAIT_SECOND = 10;
    private final Deque<Member> matchMemberQueue;
    private final MatchMemberSession matchMemberSession;
    private final GameRepository gameRepository;
    private final MessageSender messageSender;

    @Autowired
    public MatchService(MatchMemberSession matchMemberSession, GameRepository gameRepository, MessageSender messageSender) {
        this.matchMemberSession = matchMemberSession;
        this.gameRepository = gameRepository;
        this.messageSender = messageSender;
        this.matchMemberQueue = new ArrayDeque<>();
    }

    public void createMatchMemberBySessionId(String sessionId){
        Member matchMember = new Member(sessionId);

        matchMemberQueue.offer(matchMember);
        this.matchMemberSession.insertMember(matchMember);
    }

    public void deleteMatchMemberBySessionId(String sessionId){
        this.matchMemberSession.deleteMemberBySessionId(sessionId);
    }

    public void startMatch(){
        if(matchMemberQueue.size()<GameSetting.MAX_GAMEMEMBER_COUNT) return;

        try{
            Optional<Deque<Member>> gameMember = Optional.of(getGameMembers());
            Game game = gameRepository.createNewGame();

            sendEnterRequestToAllGameMember(gameMember.get(),game);

//            Thread.sleep(GAME_ENTER_REQUEST_WAIT_SECOND *1000);
            int second = GAME_ENTER_REQUEST_WAIT_SECOND;

            /**
             * ToDo
             * gameMember Que가 초기화 되지 않고 순회하도록 바꿔야함
             */
            while(second-->0){
                messageSender.sendObjectToAll("/topic/enter/"+game.getGameId(),second);

                if(game.getMembers().size() == GameSetting.MAX_GAMEMEMBER_COUNT){
                    sendEnterResult(game.getGameId());
                    GameProcessor gameProcessor = new GameProcessor(game,this.messageSender);
                    Thread thread = new Thread(gameProcessor);
                    thread.start();
                    return;
                }

                Thread.sleep(1000);
            }

            /**
             * ToDo Game 파괴시 현재 유저를 다시 매칭큐의 앞으로 이동시켜 주어야 함
             */

            gameRepository.deleteGameByGameId(game.getGameId());
            System.out.println("Game Deleted");

        }catch (NullPointerException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
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
        System.out.println("gameRepository = " + gameRepository);

        gameRepository.findGameByGameId(gameId)
                .getMembers()
                .put(sessionId,matchMemberSession.findBySessionId(sessionId));
    }

    private void sendEnterRequestToAllGameMember(Deque<Member> gameMemberQueue, Game game){
        System.out.println("Start Send Enter Request");

        while(!gameMemberQueue.isEmpty()){
            sendEnterRequestToGameMember(gameMemberQueue.poll(),game.getGameId());
        }

        System.out.println("End Send Enter Request");
    }

    private void sendEnterRequestToGameMember(Member gameMember, String gameId) {
        try {
            messageSender.sendObjectToMember(
                    gameMember.getSessionId(),
                    MATCH_DESTINATION,
                    new MatchResponse(gameMember.getSessionId(),gameId)
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendEnterResult(String gameId){
        System.out.println("Send Load Game Request");
        messageSender.sendStringToAll("/topic/enter/"+gameId,"true");
//        template.convertAndSend( "/topic/enter/"+gameId,"true");
    }
}
