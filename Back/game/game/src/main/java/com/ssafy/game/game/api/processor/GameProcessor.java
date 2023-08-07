package com.ssafy.game.game.api.processor;

import com.ssafy.game.game.db.entity.GameSession;
import com.ssafy.game.match.common.GameSetting;
import com.ssafy.game.util.MessageSender;

public class GameProcessor implements Runnable{
    private final GameSession gameSession;
    private final MessageSender sender;

    public GameProcessor(GameSession gameSession, MessageSender sender) {
        this.gameSession = gameSession;
        this.sender = sender;
    }

    @Override
    public void run() {
        waitLoadGame();
        sender.sendObjectToAll("/topic/game/"+gameSession.getSessionId(),"hello");
    }

    private boolean allMemberLoadGame(){
        if(gameSession.getGameMembers().size()== GameSetting.MAX_GAMEMEMBER_COUNT)return true;
        return false;
    }

    private void waitLoadGame(){
        int second = 30;
        while(second-->0){
            sender.sendObjectToAll("/topic/game/"+gameSession.getSessionId(),second);

            if(allMemberLoadGame()) return;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
