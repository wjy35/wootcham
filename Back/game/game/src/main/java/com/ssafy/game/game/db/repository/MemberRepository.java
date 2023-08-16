package com.ssafy.game.game.db.repository;

import com.ssafy.game.game.db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Member,Integer> {
    @Query(value = "CALL pointProc(:nickname_input, :point_change);", nativeQuery = true)
    void updateRankPoint(@Param("nickname_input") String nickname,@Param("point_change") Integer point);
}
