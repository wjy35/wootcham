package com.ssafy.game.game.api.response;

import com.ssafy.game.game.api.dto.RankPointChange;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReflectRankResponse extends GameStatusResponse{
    private List<RankPointChange> rankPointChangeList;

    public ReflectRankResponse(List<RankPointChange> rankPointChangeList) {
        super(GameStatus.REFLECT_RANK, 5);
        this.rankPointChangeList = rankPointChangeList;
    }
}
