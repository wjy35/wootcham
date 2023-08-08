package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.member.db.entity.MemberItem;
import com.ssafy.wcc.domain.member.db.repository.MemberItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberItemServiceImpl implements MemberItemService{
    final private MemberItemRepository memberItemRepository;

    @Override
    public List<MemberItem> getMemberItemList(Long id) {
        List<MemberItem> memberItemList = memberItemRepository.findAllByMemberId(id);
        return memberItemList;
    }
}
