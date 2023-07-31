package com.ssafy.wcc.domain.member.application.mapper;

import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;
import com.ssafy.wcc.domain.member.db.entity.Member;
import org.mapstruct.Mapper;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberRequestToMember(MemberRequest request);

    Optional<MemberLoginResponse> memberToMemberLoginResponse(Member member);
}
