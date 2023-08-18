package com.ssafy.game.game.api.dto;

import com.ssafy.game.common.RankSetting;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GameMemberChange {
    private String nickname;
    private Integer point;
    private Integer money;

    public GameMemberChange(String nickname, Integer point, Integer money) {
        this.nickname = nickname;
        this.money = money;

        if(point > RankSetting.MAX_RANK_POINT_CHANGE_RANGE){
            point = RankSetting.MAX_RANK_POINT_CHANGE_RANGE;
        }

        if(point < -RankSetting.MAX_RANK_POINT_CHANGE_RANGE){
            point = -RankSetting.MAX_RANK_POINT_CHANGE_RANGE;
        }

        this.point = point;
    }
}
