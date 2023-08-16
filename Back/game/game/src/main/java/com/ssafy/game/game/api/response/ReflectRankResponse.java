package com.ssafy.game.game.api.response;

import com.ssafy.game.game.api.dto.GameMemberChange;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReflectRankResponse extends GameStatusResponse{
    private List<GameMemberChange> gameMemberChangeList;

    public ReflectRankResponse(List<GameMemberChange> gameMemberChangeList) {
        super(GameStatus.REFLECT_RANK, 5);
        this.gameMemberChangeList = gameMemberChangeList;
    }
}
