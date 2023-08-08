package com.ssafy.game.game.db.entity;


import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class GameSession {
    private String sessionId;

    /**
     * key: memberToken
     * value: GameMember
     */
    private Map<String,GameMember> gameMembers;

    /**
     * key: memberToken
     * value: Topic
     */
    private Map<String,Topic> topics;
    private List<String> orderList;
    private boolean checkedSkipPreparedPresent;
    private boolean checkedSkipPresent;

    public GameSession(String sessionId) {
        this.sessionId = sessionId;
        this.gameMembers = new HashMap<>();
        this.topics = new HashMap<>();
        this.checkedSkipPreparedPresent = false;
        this.checkedSkipPresent = false;
    }

    public void pickTopic(String memberToken, Integer type, String keyword){
        topics.get(memberToken).setTopic(type, keyword);
        // ToDo merge로 변경
    }

    public void loadTopic(String memberToken){
        topics.put(memberToken,new Topic(0,"default"));
    }
}
