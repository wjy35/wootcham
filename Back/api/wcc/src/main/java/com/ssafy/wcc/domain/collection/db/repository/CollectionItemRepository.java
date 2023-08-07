package com.ssafy.wcc.domain.collection.db.repository;

import com.ssafy.wcc.domain.collection.db.entity.CollectionItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CollectionItemRepository extends JpaRepository<CollectionItem, Long> {
    List<CollectionItem> findAll();

//    Optional<CollectionItem> findById(int collectionId);

    List<CollectionItem> findAllByType(int Type);
}
