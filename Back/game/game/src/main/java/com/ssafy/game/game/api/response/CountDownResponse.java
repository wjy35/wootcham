package com.ssafy.game.game.api.response;

import com.ssafy.game.common.GameSessionSetting;
import com.ssafy.game.game.db.entity.Topic;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountDownResponse extends GameStatusResponse {
    Topic topic;

    public CountDownResponse(Topic topic) {
        super(GameStatus.COUNT_DOWN, GameSessionSetting.COUNT_DOWN_SECOND);
        this.topic = topic;
    }
}
