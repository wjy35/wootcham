package com.ssafy.wcc.domain.collection.application.service;

import com.ssafy.wcc.domain.collection.db.entity.CollectionItem;
import com.ssafy.wcc.domain.collection.db.repository.CollectionItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CollectionItemServiceImpl implements CollectionItemService{

    final private CollectionItemRepository collectionItemRepository;

    @Override
    public List<CollectionItem> getList(Long id) {
        List<CollectionItem> collectionItemList = collectionItemRepository.findAll();
        return collectionItemList;
    }
}
