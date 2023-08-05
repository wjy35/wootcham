package com.ssafy.wcc.domain.member.application.mapper;

import com.ssafy.wcc.domain.member.application.dto.request.MemberRequest;
import com.ssafy.wcc.domain.member.application.dto.response.MemberInfoResponse;
import com.ssafy.wcc.domain.member.application.dto.response.MemberLoginResponse;
import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.report.db.entity.Report;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Optional;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    MemberMapper INSTANCE = Mappers.getMapper(MemberMapper.class);

    @Mapping(source="report", target="report")
    public MemberInfoResponse toMemberInfoResponse(Member member, Integer report);

    Member memberRequestToMember(MemberRequest request);

}
