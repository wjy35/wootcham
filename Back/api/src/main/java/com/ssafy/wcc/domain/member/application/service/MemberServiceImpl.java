package com.ssafy.wcc.domain.member.application.service;

import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.mapper.MemberMapper;
import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.member.db.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;
    private final MemberMapper memberMapper;


    @Override
    // 이메일 중복 검사
    public boolean checkEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
}
