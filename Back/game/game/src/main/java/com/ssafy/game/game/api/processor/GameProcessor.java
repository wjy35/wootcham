package com.ssafy.game.game.api.processor;

import com.ssafy.game.game.db.entity.Game;
import org.springframework.messaging.simp.SimpMessageSendingOperations;

public class GameProcessor implements Runnable{
    private final Game game;
    private final SimpMessageSendingOperations template;

    public GameProcessor(Game game, SimpMessageSendingOperations template) {
        this.template = template;
        this.game = game;
    }

    @Override
    public void run() {
        waitLoadGame();
        /**
         * ToDo
         * Scheduled
         * excuteThread(excute Service)
         * observer thread
         */
    }

    private void waitLoadGame(){
        try {
            Thread.sleep(5000);
            System.out.println("Game Loading");
            sendMessageToAll("1");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private void sendMessageToAll(String message){
        template.convertAndSend("/topic/game/"+this.game.getGameId(),message);
    }
}
