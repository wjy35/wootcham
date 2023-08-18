package com.ssafy.game.game.api.request;

import lombok.Data;

@Data
public class SkipPickTopicRequest {
    private String sessionId;
    private String memberToken;
}
