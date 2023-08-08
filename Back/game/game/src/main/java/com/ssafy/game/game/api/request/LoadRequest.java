package com.ssafy.game.game.api.request;

import lombok.Data;

@Data
public class LoadRequest {
    private String memberId;
    private String memberToken;
}
