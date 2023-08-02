package com.ssafy.game.match.api.request;

public class Member {
    private String sessionId;
    private boolean connected;


    public Member(String sessionId) {
        this.sessionId = sessionId;
        this.connected = true;
    }

    public String getSessionId(){
        return this.sessionId;
    }

    public boolean isConnected(){ return connected; }

    public void disconnect(){
        this.connected = false;
    }
}