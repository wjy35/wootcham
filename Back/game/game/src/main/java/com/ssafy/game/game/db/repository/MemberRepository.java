package com.ssafy.game.game.db.repository;

import com.ssafy.game.game.db.entity.Test;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface MemberRepository extends CrudRepository<Test,Integer> {

    @Procedure("pointProc")
    void updateRankPoint(@Param("nickname_input") String nickname,@Param("point_change") Integer point);

}
