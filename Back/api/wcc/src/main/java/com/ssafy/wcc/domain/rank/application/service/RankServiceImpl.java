package com.ssafy.wcc.domain.rank.application.service;

import com.ssafy.wcc.common.exception.WCCException;
import com.ssafy.wcc.domain.member.application.dto.response.MemberInfoResponse;
import com.ssafy.wcc.domain.member.application.mapper.MemberMapper;
import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.notice.presentation.NoticeController;
import com.ssafy.wcc.domain.rank.db.repository.RankRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RankServiceImpl implements RankService{

    Logger logger = LoggerFactory.getLogger(NoticeController.class);

    private final RankRepository repository;

    private final MemberMapper memberMapper;

    public List<MemberInfoResponse> getRank() throws RuntimeException {
        logger.info("getRank service 진입");

        try{
            List<Member> rankList = repository.findTop10ByOrderByPointDesc();
            List<MemberInfoResponse> result = new ArrayList<>();
            for(int i=0; i<rankList.size(); i++){
                MemberInfoResponse memberInfoResponse = memberMapper.toMemberInfoResponse(rankList.get(i));
                result.add(memberInfoResponse);
            }
            return result;
        }catch (RuntimeException e){
            throw new RuntimeException();
        }
    }
}
