package com.ssafy.game.game.api.response;

public class PresentResponse extends GameStatusResponse{
    private String tellerToken;
    private String topic;

    public PresentResponse(String tellerToken, int second,String topic) {
        super(GameStatus.PRESENT, second);
        this.tellerToken = tellerToken;
        this.topic = topic;
    }
}
