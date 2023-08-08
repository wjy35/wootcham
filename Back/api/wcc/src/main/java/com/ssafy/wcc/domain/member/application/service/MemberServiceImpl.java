package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.collection.db.entity.CollectionItem;
import com.ssafy.wcc.domain.collection.db.repository.CollectionItemRepository;
import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberInfoResponse;
import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;
import com.ssafy.wcc.domain.member.application.mapper.MemberMapper;
import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.member.db.entity.MemberItem;
import com.ssafy.wcc.domain.member.db.entity.MemberItemPK;
import com.ssafy.wcc.domain.member.db.repository.MemberItemRepository;
import com.ssafy.wcc.domain.member.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final MemberMapper memberMapper;

    private final CollectionItemRepository collectionItemRepository;

    private final MemberItemRepository memberItemRepository;


    @Override
    public void memberSignUp(MemberRequest signupInfo) {
        // 비밀번호에 암호 적용
        Member member = memberMapper.memberRequestToMember(signupInfo);

        member.setMoney(0);
        member.setAdmin(1);
        member.setPoint(0);
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
    public Long memberLogin(MemberRequest loginInfo) throws RuntimeException {
        Optional<Member> findMember = memberRepository.findByEmail(loginInfo.getEmail());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!findMember.isPresent()) {
            throw new RuntimeException("없는 아이디입니다.");
        }
        if (!encoder.matches(loginInfo.getPassword(), findMember.get().getPassword())) {
            throw new RuntimeException("잘못된 비밀번호입니다.");
        }

        if(findMember.get().getSuspensionDay() != null){
            LocalDate date1 = findMember.get().getSuspensionDay();
            LocalDate date2 = LocalDate.now();
            if(date1.compareTo(date2) >= 0){
                throw new RuntimeException("정지된 유저입니다");
            }
        }

        return findMember.get().getId();
    }

    @Override
    public void memberDelete(String id) throws RuntimeException {
        memberRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public void memberUpdate(MemberRequest memberRequest, String id) {
        Optional<Member> member = memberRepository.findById(Long.parseLong(id));
        if (member.isPresent()) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String securePassword = encoder.encode(memberRequest.getPassword());
            member.get().setNickname(memberRequest.getNickname());
            member.get().setPassword(securePassword);
            memberRepository.save(member.get());
        }
    }

    @Override
    public boolean checkEmail(String email) {
        return memberRepository.existsByEmail(email);
    }


    @Override
    public MemberInfoResponse memberInfoResponse(Long id) throws RuntimeException {
        Optional<Member> findMember = memberRepository.findById(id);
        if (findMember.isPresent()) {
            MemberInfoResponse memberInfoResponse = memberMapper.toMemberInfoResponse(findMember.get());
            return memberInfoResponse;
        }
        return null;
    }

    @Override
    public boolean checkNickname(String nickname) throws RuntimeException {
        long count = memberRepository.countByNickname(nickname);
        if(count == 0){
            return true;
        }
        return false;
    }
}
