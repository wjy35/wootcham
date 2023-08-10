package com.ssafy.game.game.api.request;

import lombok.Data;

@Data
public class UpSmileCountRequest {
    private String sessionId;
    private String memberToken;
}
