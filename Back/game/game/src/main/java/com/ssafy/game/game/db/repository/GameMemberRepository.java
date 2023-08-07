package com.ssafy.game.game.db.repository;

import com.ssafy.game.game.db.entity.GameMember;
import org.springframework.stereotype.Repository;
import java.util.Map;

@Repository
public class GameMemberRepository {
    private Map<String, GameMember> db;

    public void save(GameMember gameMember){
        db.put(gameMember.getMemberId(),gameMember);
    }
}
