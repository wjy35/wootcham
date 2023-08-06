package com.ssafy.wcc.domain.collection.db.repository;

import com.ssafy.wcc.domain.collection.db.entity.CollectionItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CollectionItemRepository extends JpaRepository<CollectionItem, Long> {
    List<CollectionItem> findAll();

}
