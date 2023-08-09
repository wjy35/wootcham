package com.ssafy.wcc.domain.rank.db.repository;

import com.ssafy.wcc.domain.member.db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RankRepository extends JpaRepository<Member, Long> {
    List<Member> findTop10ByOrderByPointDesc();
}
