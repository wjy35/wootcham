package com.ssafy.wcc.domain.member.db.repository;

import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.member.db.entity.MemberItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{

    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

    @Override
    Optional<Member> findById(Long id);

    Long countByNickname(String nickname);

}
