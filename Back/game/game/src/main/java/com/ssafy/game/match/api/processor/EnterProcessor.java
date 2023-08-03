package com.ssafy.game.match.api.processor;


import com.ssafy.game.game.db.entity.Game;
import com.ssafy.game.match.common.GameSetting;
import com.ssafy.game.util.MessageSender;

public class EnterProcessor implements Runnable{

    private MessageSender messageSender;
    private Game game;

    public EnterProcessor(MessageSender messageSender, Game game) {
        this.messageSender = messageSender;
        this.game = game;
    }

    @Override
    public void run() {
        int second = GameSetting.MAX_GAMEMEMBER_ENTER_WAIT_SECOND;

        while (second-->0){
            if(this.game.getMembers().size() == GameSetting.MAX_GAMEMEMBER_COUNT){
                return;
            }
        }
    }
}
