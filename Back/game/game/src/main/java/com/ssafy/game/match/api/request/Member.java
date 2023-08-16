package com.ssafy.game.match.api.request;

public class Member {
    private String memberId;
    private String nickname;
    private boolean connected;

    public Member(String memberId) {
        this.memberId = memberId;
        this.connected = true;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMemberId(){
        return this.memberId;
    }

    public String getNickname() {
        return nickname;
    }

    public boolean isConnected(){ return connected; }

    public void disconnect(){
        this.connected = false;
    }
}