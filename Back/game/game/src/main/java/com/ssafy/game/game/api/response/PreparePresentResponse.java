package com.ssafy.game.game.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PreparePresentResponse extends GameStatusResponse {

    private String tellerToken;

    public PreparePresentResponse(String tellerToken,int second) {
        super(GameStatus.PREPARE_PRESENT,second);
        this.tellerToken = tellerToken;
    }
}