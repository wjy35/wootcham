package com.ssafy.game.match.api.processor;


import com.ssafy.game.match.db.entity.Group;
import com.ssafy.game.match.common.GameSetting;
import com.ssafy.game.util.MessageSender;

public class EnterProcessor implements Runnable{

    private MessageSender messageSender;
    private Group group;

    public EnterProcessor(MessageSender messageSender, Group group) {
        this.messageSender = messageSender;
        this.group = group;
    }

    @Override
    public void run() {
        int second = GameSetting.MAX_GAMEMEMBER_ENTER_WAIT_SECOND;

        while (second-->0){
            if(this.group.getMembers().size() == GameSetting.MAX_GAMEMEMBER_COUNT){
                return;
            }
        }
    }
}
