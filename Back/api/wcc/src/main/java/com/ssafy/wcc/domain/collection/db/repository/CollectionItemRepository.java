package com.ssafy.wcc.domain.collection.db.repository;

import com.ssafy.wcc.domain.collection.db.entity.CollectionItem;
import com.ssafy.wcc.domain.member.db.entity.MemberItem;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CollectionItemRepository extends JpaRepository<CollectionItem, Long> {
    List<CollectionItem> findAll();

    List<CollectionItem> findAllByType(int Type);

    @Query(
            value = "select url \n" +
                    "from collection_item \n" +
                    "where `type` = :type and id in (select collection_id \n" +
                    "from member_item \n" +
                    "where member_id = :memberId and wear = 1)",
            nativeQuery = true)
    // type: 1-프로필, 2-휘장, 3-테두리
    // 현재 착용 중인 타입의 아이템 이미지 url을 가져온다.
    public String getCurrentItemImage(@Param("memberId") Long memberId, @Param("type") Integer type);
}
