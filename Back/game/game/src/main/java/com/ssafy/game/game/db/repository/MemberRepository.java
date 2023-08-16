package com.ssafy.game.game.db.repository;

import com.ssafy.game.game.db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    @Procedure("pointProc")
    int updateRankPoint(String nickname_input,Integer point_change);
}
