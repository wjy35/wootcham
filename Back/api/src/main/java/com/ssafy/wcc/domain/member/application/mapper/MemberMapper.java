package com.ssafy.wcc.domain.member.application.mapper;

import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;
import com.ssafy.wcc.domain.member.db.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member memberRequestToMember(MemberRequest request);

    MemberLoginResponse memberToMemberLoginResponse(Member member);
}
