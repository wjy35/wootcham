package com.ssafy.game.game.api.processor;

import com.ssafy.game.common.GameSessionSetting;
import com.ssafy.game.game.api.response.GameOrderResponse;
import com.ssafy.game.game.api.response.GameStatus;
import com.ssafy.game.game.api.response.GameStatusResponse;
import com.ssafy.game.game.db.entity.GameSession;
import com.ssafy.game.match.common.GameSetting;
import com.ssafy.game.util.MessageSender;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

        for(int i=0; i<GameSetting.ROUND_COUNT; i++){
            round();
        }

    }

    private void round(){
        orderGameMember();
    }

    private void orderGameMember(){
        List<String> gameMemberOrder = getShuffledGameMemberIdList();
        createAndsendGameMemberOrderResponse(gameMemberOrder);
    }

    private List<String> getShuffledGameMemberIdList(){
        List<String> gameMemberOrder = new ArrayList<>(gameSession.getGameMembers().keySet());
        Collections.shuffle(gameMemberOrder);

        return gameMemberOrder;
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

    private void createAndsendGameMemberOrderResponse(List<String> order){
        sender.sendObjectToAll(gameDestination,new GameOrderResponse(GameStatus.ORDER_GAMEMEMBER,order));
    }

    private boolean allMemberLoadGame(){
        if(gameSession.getGameMembers().size()== GameSetting.MAX_GAMEMEMBER_COUNT)return true;
        return false;
    }
}
