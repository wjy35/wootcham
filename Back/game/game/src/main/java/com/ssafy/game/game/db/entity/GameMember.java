package com.ssafy.game.game.db.entity;


public class GameMember {
    String memberId;
    boolean connected;

    public GameMember(String memberId) {
        this.memberId = memberId;
        this.connected = true;
    }
}
