package com.ssafy.wcc.domain.member.db.repository;

import com.ssafy.wcc.domain.member.db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{

    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);

    Long countByNickname(String nickname);

    @Query("SELECT m.nickname FROM Member m WHERE m.id = :memberId")
    Optional<String> findNicknameById(@Param("memberId") Long userId);

    String getMemberNickname(Long id);
}
