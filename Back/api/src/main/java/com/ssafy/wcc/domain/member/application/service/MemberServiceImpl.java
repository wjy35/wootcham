package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.mapper.MemberMapper;
import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.member.db.repository.MemberRepository;
import com.ssafy.wcc.domain.jwt.db.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final MemberMapper memberMapper;

//    private final JwtUtil jwtUtil;

    private final TokenRepository tokenRepository;

    @Override
    public void memberSignUp(MemberRequest signupInfo) {
        // 비밀번호에 암호 적용
//        String encodePassword = passwordEncoder.encode(signupInfo.getPassword());

        Member member = memberMapper.memberRequestToMember(signupInfo);
//        member.updatePassword(encodePassword);

        Member saveMember = memberRepository.save(member);
    }

    @Override
    public void memberLogin(MemberRequest loginInfo) throws RuntimeException{
        // DB에서 같은 이메일을 가진 유저 검색
        Optional<Member> findMember = memberRepository.findByEmail(loginInfo.getEmail());

//        if (passwordEncoder.matches(loginInfo.getPassword(), findMember.getPassword())) { // 비밀번호가 일치하는 경우
        if(!findMember.isPresent()){
            throw new RuntimeException("없는 아이디입니다.");
        }
        if(!loginInfo.getPassword().equals(findMember.get().getPassword())) {
            throw new RuntimeException("잘못된 비밀번호입니다.");
        }
    }

    @Override
    // 이메일 중복 검사
    public boolean checkEmail(String email) {
        return memberRepository.existsByEmail(email);
    }


    // header에서 가져온 refreshToken을 키값으로 redis에서 email조회
    // redis namespace 나눈 뒤 구현
    @Override
    public String getMemberEmail(String refreshToken) {
        return null;
    }
}
