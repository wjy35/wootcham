package com.ssafy.wcc.domain.member.db.repository;

import com.ssafy.wcc.domain.member.db.entity.MemberItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public interface MemberItemRepository extends JpaRepository<MemberItem, Long> {
    List<MemberItem> findAllByMemberId(Long id);
    Optional<MemberItem> findByMemberIdAndCollectionId(Long memberId, Long collectionId);
}
