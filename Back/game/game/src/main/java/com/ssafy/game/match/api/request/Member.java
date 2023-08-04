package com.ssafy.game.match.api.request;

public class Member {
    private String memberId;
    private boolean connected;

    public Member(String memberId) {
        this.memberId = memberId;
        this.connected = true;
    }

    public String getMemberId(){
        return this.memberId;
    }

    public boolean isConnected(){ return connected; }

    public void disconnect(){
        this.connected = false;
    }
}