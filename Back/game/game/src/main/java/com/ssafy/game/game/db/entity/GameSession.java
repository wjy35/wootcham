package com.ssafy.game.game.db.entity;


import com.ssafy.game.common.GameSessionSetting;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

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
    private Set<String> pickedGameMembers;
    private boolean checkedSkipPreparedPresent;
    private boolean checkedSkipPresent;
    private Map<String,Integer> smileCount;
    private Map<String, LocalDateTime> disconnectTime;

    public GameSession(String sessionId) {
        this.sessionId = sessionId;
        this.gameMembers = new HashMap<>();
        this.topics = new HashMap<>();
        this.checkedSkipPreparedPresent = false;
        this.checkedSkipPresent = false;
        this.smileCount = new HashMap<>();
        this.disconnectTime = new HashMap<>();
        this.pickedGameMembers = new HashSet<>();
    }

    public void clearPickedGameMembers(){
        this.pickedGameMembers = new HashSet<>();
    }

    public void addPickedGameMembers(String memberToken) {
        this.pickedGameMembers.add(memberToken);
    }
    public void pickTopic(String memberToken, Integer type, String keyword){
        topics.get(memberToken).setTopic(type, keyword);
        // ToDo merge로 변경
    }

    public void upSmileCount(String memberToken){
        updateSmileCount(memberToken,1);
    }

    public void loadTopic(String memberToken){
        topics.put(memberToken,new Topic(0,"default"));
    }

    public void loadSmileCount(String memberToken) { smileCount.put(memberToken,0); }

    public void updateSmileCount(String memberToken,int count){
        // ToDo merge 로 변경
        smileCount.replace(memberToken,smileCount.get(memberToken)+count);
    }

    public void changePresentOrder(){
        orderList.add(orderList.get(0));
        orderList.remove(0);
    }

    public void commitTopic(String memberToken, Integer type, String keyword, Boolean useTopic, Boolean displayTopic){
        topics.get(memberToken).setTopic(type,keyword,useTopic,displayTopic);
    }
    
    public void disconnect(String memberToken){
        disconnectTime.put(memberToken,LocalDateTime.now());
    }

    public void reconnect(String memberToken){
        int count = ((int)Duration.between(disconnectTime.get(memberToken),LocalDateTime.now()).toMillis())/ (GameSessionSetting.SMILE_COUNT_CHECK_INTERVAL_SECOND * 1000);
        disconnectTime.remove(memberToken);
        updateSmileCount(memberToken,count);
    }
}
