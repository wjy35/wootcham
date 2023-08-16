package com.ssafy.game.game.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends JpaRepository<Void,Integer> {

    @Procedure("pointProc")
    void updateRankPoint(@Param("nickname_input") String nickname,@Param("point_change") Integer point);

}
