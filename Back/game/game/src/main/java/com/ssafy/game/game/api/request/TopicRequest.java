package com.ssafy.game.game.api.request;

import lombok.Data;

@Data
public class TopicRequest {
    private Integer type;
    private String keyword;
}
