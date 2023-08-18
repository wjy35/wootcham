package com.ssafy.game.game.api.request;

import lombok.Data;

@Data
public class SkipPrepareRequest {
    private Integer type;
    private String keyword;
    private String memberToken;
    private Boolean useTopic;
    private Boolean displayTopic;
}
