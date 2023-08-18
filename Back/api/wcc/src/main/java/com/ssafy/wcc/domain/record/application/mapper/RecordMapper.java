package com.ssafy.wcc.domain.record.application.mapper;

import com.ssafy.wcc.domain.member.application.mapper.MemberMapper;
import com.ssafy.wcc.domain.record.application.dto.response.RecordResponse;
import com.ssafy.wcc.domain.record.db.entity.Record;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface RecordMapper {
    RecordMapper INSTANCE = Mappers.getMapper(RecordMapper.class);

    RecordResponse toRecordResponse(Record record);
}
