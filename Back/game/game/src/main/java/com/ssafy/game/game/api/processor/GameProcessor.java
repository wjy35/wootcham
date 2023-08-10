package com.ssafy.game.game.api.processor;

import com.ssafy.game.common.GameSessionSetting;
import com.ssafy.game.game.api.response.*;
import com.ssafy.game.game.db.entity.GameSession;
import com.ssafy.game.match.common.GameSetting;
import com.ssafy.game.util.MessageSender;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameProcessor implements Runnable{
    private final GameSession gameSession;
    private final MessageSender sender;
    private final String gameDestination;

    public GameProcessor(GameSession gameSession, MessageSender sender) {
        this.gameSession = gameSession;
        this.sender = sender;
        this.gameDestination = "/topic/game/"+gameSession.getSessionId();
    }

    @Override
    public void run() {
        waitGameLoad();
        waitGameStart();

        for(int round=0; round<GameSetting.ROUND_COUNT; round++){
            roundProcess(round);
        }
    }

    private void roundProcess(int round){
        roundSetting(round);
        pickTopicType();
        pickTopicKeyword();

        for(int i=0; i<gameSession.getOrderList().size(); i++){
            preparePresent(i);
            present(i);
            System.out.println("gameSession.getTopics() = " + gameSession.getTopics());
        }
    }

    private void preparePresent(int order){
        int second = GameSessionSetting.MAX_PREPARE_PRESENT_SECOND;

        String teller = gameSession.getOrderList().get(order);
        PreparePresentResponse preparePresentResponse = new PreparePresentResponse(teller,second);
        gameSession.setCheckedSkipPreparedPresent(false);

        try{
            while(second-->0){
                preparePresentResponse.setSecond(second);
                sendGameStatusResponse(preparePresentResponse);

                if(gameSession.isCheckedSkipPreparedPresent()){
                    return;
                }

                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void present(int order){
        int second = GameSessionSetting.MAX_PRESENT_SECOND;

        String teller = gameSession.getOrderList().get(order);
        gameSession.setCheckedSkipPresent(false);
        PresentResponse presentResponse = new PresentResponse(teller,second,gameSession.getTopics().get(teller).toString());

        try{
            while(second-->0){
                presentResponse.setSecond(second);
                sendGameStatusResponse(presentResponse);

                if(gameSession.isCheckedSkipPresent()) return;
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void pickTopicKeyword(){
        int second = GameSessionSetting.MAX_PICK_TOPIC_KEYWORD_SECOND;
        GameStatusResponse gameStatusResponse = new GameStatusResponse(GameStatus.PICK_TOPIC_KEYWORD,second);

        try{
            while(second-->0){
                gameStatusResponse.setSecond(second);
                sendGameStatusResponse(gameStatusResponse);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void pickTopicType(){
        int second = GameSessionSetting.MAX_PICK_TOPIC_TYPE_SECOND;
        GameStatusResponse gameStatusResponse = new GameStatusResponse(GameStatus.PICK_TOPIC_TYPE,second);

        try{
            while(second-->0){
                gameStatusResponse.setSecond(second);
                sendGameStatusResponse(gameStatusResponse);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void roundSetting(int round){
        List<String> shuffledGameMemberTokenList = getShuffledGameMemberTokenList();
        gameSession.setOrderList(shuffledGameMemberTokenList);
        RoundSettingResponse roundSettingResponse = new RoundSettingResponse(shuffledGameMemberTokenList,round);
        sendRoundSettingResponse(roundSettingResponse);
    }

    private List<String> getShuffledGameMemberTokenList(){
        List<String> gameMemberOrderList = gameSession
                .getGameMembers()
                .values()
                .stream()
                .map(gameMember -> gameMember.getMemberToken())
                .collect(Collectors.toCollection(ArrayList::new));

        Collections.shuffle(gameMemberOrderList);

        return gameMemberOrderList;
    }

    private void waitGameLoad(){
        int second = GameSetting.MAX_GAME_LOAD_SECOND;
        while(second-->0){
            sender.sendObjectToAll(gameDestination,second);

            if(allMemberLoadGame()) return;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void waitGameStart(){
        int second = GameSessionSetting.MAX_GAME_WAIT_SECOND;
        GameStatusResponse gameStatusResponse = new GameStatusResponse(GameStatus.WAIT_GAME_START,second);

        try{
            while(second-->0){
                gameStatusResponse.setSecond(second);
                sendGameStatusResponse(gameStatusResponse);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void sendGameStatusResponse(GameStatusResponse gameStatusResponse){
        sender.sendObjectToAll(gameDestination,gameStatusResponse);
    }

    private void sendRoundSettingResponse(RoundSettingResponse roundSettingResponsee){
        sender.sendObjectToAll(gameDestination,roundSettingResponsee);
    }

    private boolean allMemberLoadGame(){
        if(gameSession.getGameMembers().size()==GameSetting.MAX_GAMEMEMBER_COUNT) return true;

        return false;
    }
}
