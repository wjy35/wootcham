package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberInfoResponse;
import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;
import com.ssafy.wcc.domain.member.application.mapper.MemberMapper;
import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.member.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    private final MemberMapper memberMapper;


    @Override
    public void memberSignUp(MemberRequest signupInfo) {
        // 비밀번호에 암호 적용
        Member member = memberMapper.memberRequestToMember(signupInfo);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String securePassword = encoder.encode(member.getPassword());

        member.setPassword(securePassword);

        memberRepository.save(member);

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
        return findMember.get().getId();
    }

    @Override
    public void memberDelete(String id) throws RuntimeException {
        memberRepository.deleteById(Long.parseLong(id));
    }

    @Override
    public void memberUpdate(MemberRequest memberRequest, String id) {
        Optional<Member> member = memberRepository.findById(Long.parseLong(id));
        if(member.isPresent()){
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
    public MemberInfoResponse memberInfoResponse(Long id) throws  RuntimeException{
      Optional<Member> findMember = memberRepository.findById(id);
      if (findMember.isPresent()) {
          int report = findMember.get().getReports().get(0).getReport();
          MemberInfoResponse memberInfoResponse = memberMapper.toMemberInfoResponse(findMember.get(), report);
          return memberInfoResponse;
      }
      return null;
    }
}
