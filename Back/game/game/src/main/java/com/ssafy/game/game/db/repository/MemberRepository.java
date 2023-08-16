package com.ssafy.game.game.db.repository;

import com.ssafy.game.game.db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    @Procedure("pointProc")
    int updateRankPoint(@Param("nickname_input") String nickname,@Param("point_change") Integer point);
}
