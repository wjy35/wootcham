package com.ssafy.wcc.domain.collection.application.service;

import com.ssafy.wcc.domain.collection.db.entity.CollectionItem;

import java.util.List;
import java.util.Optional;

public interface CollectionItemService {
    List<CollectionItem> getList(Long id);
}
