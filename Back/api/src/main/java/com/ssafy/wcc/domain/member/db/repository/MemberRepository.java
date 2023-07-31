package com.ssafy.wcc.domain.member.db.repository;

import com.ssafy.wcc.domain.member.db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);

    Member findByEmail(String email);
}
