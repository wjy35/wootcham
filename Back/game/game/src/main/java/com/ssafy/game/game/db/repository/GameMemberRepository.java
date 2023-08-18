package com.ssafy.game.game.db.repository;

import com.ssafy.game.game.db.entity.GameMember;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class GameMemberRepository {
    /**
     * key: memberId
     */
    private Map<String, GameMember> db;

    public GameMemberRepository() {
        this.db = new HashMap<>();
    }

    public void save(GameMember gameMember){
        db.put(gameMember.getMemberId(),gameMember);
    }

    public GameMember findByMemberId(String memberId){
        return db.get(memberId);
    }
}
