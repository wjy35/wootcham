package com.ssafy.wcc.domain.member.db.repository;

import com.ssafy.wcc.domain.member.db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long>{

    boolean existsByEmail(String email);

    Optional<Member> findByEmail(String email);

    @Query("SELECT COUNT(*) FROM Member")
    Long countById();

    Long countByNickname(String nickname);

    @Query("SELECT m.nickname FROM Member m WHERE m.id = :memberId")
    Optional<String> findNicknameById(@Param("memberId") Long userId);

    @Query(nativeQuery = true,
            value = "SELECT r.ranking \n" +
            "FROM (SELECT id, email, point, dense_rank() OVER (ORDER BY point DESC) AS ranking \n" +
                    "FROM member) AS r \n" +
            "where id = :memberId")
    Long getRanking(@Param("memberId") Long userId);
}
