package com.ssafy.wcc.domain.member.application.mapper;

import com.ssafy.wcc.domain.collection.db.entity.CollectionItem;
import com.ssafy.wcc.domain.member.application.dto.request.MemberItemRequest;
import com.ssafy.wcc.domain.member.db.entity.MemberItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MemberItemMapper {
    MemberItemMapper INSTANCE = Mappers.getMapper(MemberItemMapper.class);


    @Mapping(source="collectionId", target="memberItemPK.collectionId")
    @Mapping(source="memberId", target="memberItemPK.memberId")
    MemberItem memberItemRequestToMemberItem(MemberItemRequest request);
}
