package com.ssafy.game.game.api.processor;

import com.ssafy.game.match.db.entity.Group;
import com.ssafy.game.util.MessageSender;

public class GameProcessor implements Runnable{
    private final Group group;
    private final MessageSender sender;

    public GameProcessor(Group group, MessageSender sender) {
        this.group = group;
        this.sender = sender;
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
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
