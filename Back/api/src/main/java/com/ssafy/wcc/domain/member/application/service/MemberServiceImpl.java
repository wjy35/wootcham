package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;
import com.ssafy.wcc.domain.member.application.mapper.MemberMapper;
import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.member.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    private final MemberMapper memberMapper;

    @Override
    public void memberSignUp(MemberRequest signupInfo) {
        // 비밀번호에 암호 적용
//        String encodePassword = passwordEncoder.encode(signupInfo.getPassword());

        Member member = memberMapper.memberRequestToMember(signupInfo);
//        member.updatePassword(encodePassword);

        Member saveMember = memberRepository.save(member);
    }

    @Override
    public MemberLoginResponse memberLogin(MemberRequest loginInfo) {
        // DB에서 같은 이메일을 가진 유저 검색
        Member findMember = memberRepository.findByEmail(loginInfo.getEmail());

//        if (passwordEncoder.matches(loginInfo.getPassword(), findMember.getPassword())) { // 비밀번호가 일치하는 경우
        if(loginInfo.getPassword().equals(findMember.getPassword())) {
            return memberMapper.memberToMemberLoginResponse(findMember);
        }
        return null;
    }

    @Override
    // 이메일 중복 검사
    public boolean checkEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
}
