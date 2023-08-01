package com.ssafy.game.match.api.request;

public class MatchMember {
    private String sessionId;
    private boolean connected;


    public MatchMember(String sessionId) {
        this.sessionId = sessionId;
        this.connected = true;
    }

    public String getSessionId(){
        return this.sessionId;
    }

    public boolean isConnected(){ return connected; }
}