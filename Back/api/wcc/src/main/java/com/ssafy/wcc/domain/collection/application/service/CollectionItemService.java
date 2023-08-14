package com.ssafy.wcc.domain.collection.application.service;

import com.ssafy.wcc.common.exception.WCCException;
import com.ssafy.wcc.domain.collection.application.dto.response.CollectionResponse;
import com.ssafy.wcc.domain.collection.db.entity.CollectionItem;
import com.ssafy.wcc.domain.member.db.entity.MemberItem;

import java.util.List;
import java.util.Optional;

public interface CollectionItemService {
    List<CollectionResponse> getCollectionList(Long id) throws WCCException;

    boolean buy(Long memberId, int collectionId);

    boolean wear(Long memberId, int collectionId);

    int getPrice(int collectionId);

    int getType(int collectionId);

    void takeOffItem(int type, Long memberId);
}
