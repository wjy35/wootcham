package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.member.db.entity.MemberItem;

import java.util.List;

public interface MemberItemService {
    public List<MemberItem> getMemberItemList(Long id);
}
