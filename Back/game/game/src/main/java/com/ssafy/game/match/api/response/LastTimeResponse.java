package com.ssafy.game.match.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LastTimeResponse {
    private boolean isSuccess;
    private int second;

    public LastTimeResponse(int second) {
        this.isSuccess = true;
        this.second = second;
    }
}
