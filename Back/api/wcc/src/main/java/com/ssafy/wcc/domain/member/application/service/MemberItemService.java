package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.member.db.entity.MemberItem;

import java.util.List;

public interface MemberItemService {
    public List<MemberItem> getList(Long id);
}
