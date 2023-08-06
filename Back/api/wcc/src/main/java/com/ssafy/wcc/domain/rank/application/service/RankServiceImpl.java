package com.ssafy.wcc.domain.rank.application.service;

import com.ssafy.wcc.domain.member.application.dto.response.MemberInfoResponse;
import com.ssafy.wcc.domain.member.application.mapper.MemberMapper;
import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.rank.db.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankServiceImpl implements RankService{
    private final RankRepository repository;

    private final MemberMapper memberMapper;

    @Transactional
    public List<MemberInfoResponse> getRank() throws RuntimeException {

        List<Member> rankList = repository.findTop10ByOrderByPointDesc();
        List<MemberInfoResponse> result = new ArrayList<>();
        for(int i=0; i<rankList.size(); i++){
            int report = rankList.get(i).getReports().get(0).getReport();
            MemberInfoResponse memberInfoResponse = memberMapper.toMemberInfoResponse(rankList.get(i), report);
            result.add(memberInfoResponse);
        }
        return result;
    }
}
