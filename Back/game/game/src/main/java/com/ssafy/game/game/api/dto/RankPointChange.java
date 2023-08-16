package com.ssafy.game.game.api.dto;

import com.ssafy.game.common.RankSetting;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RankPointChange {
    private String memberToken;
    private Integer rankPoint;

    public RankPointChange(String memberToken, Integer rankPoint) {
        this.memberToken = memberToken;

        if(rankPoint > RankSetting.MAX_RANK_POINT_CHANGE_RANGE){
            rankPoint = RankSetting.MAX_RANK_POINT_CHANGE_RANGE;
        }

        this.rankPoint = rankPoint;
    }
}
