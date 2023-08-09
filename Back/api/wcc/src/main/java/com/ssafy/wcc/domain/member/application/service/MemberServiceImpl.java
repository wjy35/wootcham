package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.common.exception.Error;
import com.ssafy.wcc.common.exception.WCCException;
import com.ssafy.wcc.domain.collection.db.entity.CollectionItem;
import com.ssafy.wcc.domain.collection.db.repository.CollectionItemRepository;
import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.request.MemberloginRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberInfoResponse;
import com.ssafy.wcc.domain.member.application.mapper.MemberMapper;
import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.member.db.entity.MemberItem;
import com.ssafy.wcc.domain.member.db.entity.MemberItemPK;
import com.ssafy.wcc.domain.member.db.repository.MemberItemRepository;
import com.ssafy.wcc.domain.member.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    private final CollectionItemRepository collectionItemRepository;

    private final MemberItemRepository memberItemRepository;

    @Override
    public void memberSignUp(MemberRequest signupInfo) throws WCCException {
        // 비밀번호에 암호 적용
        logger.info("memberSignUp service 진입");
        Member member = memberMapper.memberRequestToMember(signupInfo);

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String securePassword = encoder.encode(member.getPassword());

        member.setPassword(securePassword);
        memberRepository.save(member);
        Optional<Member> findMember = memberRepository.findByEmail(member.getEmail());
        List<CollectionItem> collectionItemList = collectionItemRepository.findAll();
        for(int i=0; i<collectionItemList.size(); i++){
            MemberItemPK memberItemPK = MemberItemPK.builder().memberId(findMember.get().getId()).collectionId(collectionItemList.get(i).getId()).build();
            Optional<CollectionItem> collectionItem = collectionItemRepository.findById(memberItemPK.getCollectionId());
            MemberItem memberItem = MemberItem.builder().buy(false).wear(false).memberItemPK(memberItemPK).member(findMember.get()).collection(collectionItem.get()).build();
            memberItemRepository.save(memberItem);
        }
    }

    @Override
    public Long memberLogin(MemberloginRequest loginInfo) throws WCCException {
        logger.info("memberLogin service 진입");
        Optional<Member> findMember = memberRepository.findByEmail(loginInfo.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!findMember.isPresent()) {
            throw new WCCException(Error.USER_NOT_FOUND);
        }
        if (!encoder.matches(loginInfo.getPassword(), findMember.get().getPassword())) {
            throw new WCCException(Error.PASSWORD_NOT_MATCH);
        }
        if(findMember.get().getSuspensionDay() != null){
            LocalDate date1 = findMember.get().getSuspensionDay();
            LocalDate date2 = LocalDate.now();
            if(date1.compareTo(date2) >= 0){
                throw new WCCException(Error.SUSPENDED_USER);
            }
        }
        return findMember.get().getId();
    }

    @Override
    public void memberDelete(String id) throws WCCException {
        logger.info("memberDelete service 진입");
        memberRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public void memberUpdate(MemberRequest memberRequest, String id) throws WCCException{
        logger.info("memberUpdate service 진입");
        Optional<Member> member = memberRepository.findById(Long.parseLong(id));
        if (member.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String securePassword = encoder.encode(memberRequest.getPassword());
            member.get().setNickname(memberRequest.getNickname());
            member.get().setPassword(securePassword);
            memberRepository.save(member.get());
        }else{
            throw new WCCException(Error.USER_NOT_FOUND);
        }
    }

    @Override
    public boolean checkEmail(String email) {
        logger.info("checkEmail service 진입");
        return memberRepository.existsByEmail(email);
    }


    @Override
    public MemberInfoResponse memberInfoResponse(Long id) throws WCCException {
        logger.info("memberInfoResponse service 진입");
        Optional<Member> findMember = memberRepository.findById(id);
        if (findMember.isPresent()) {
            MemberInfoResponse memberInfoResponse = memberMapper.toMemberInfoResponse(findMember.get());
            return memberInfoResponse;
        }
        throw new WCCException(Error.USER_NOT_FOUND);
    }

    @Override
    public boolean checkNickname(String nickname) throws WCCException {
        logger.info("checkNickname service 진입");
        long count = memberRepository.countByNickname(nickname);
        if(count == 0){
            return true;
        }
        return false;
    }

}
