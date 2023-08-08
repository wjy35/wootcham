package com.ssafy.wcc.domain.notice.application.mapper;

import com.ssafy.wcc.domain.notice.application.dto.resonse.NoticeForAdminResponse;
import com.ssafy.wcc.domain.notice.application.dto.resonse.NoticeForUserResponse;
import com.ssafy.wcc.domain.notice.db.entity.Notice;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NoticeMapper {
    NoticeMapper INSTANCE = Mappers.getMapper(NoticeMapper.class);

    public NoticeForUserResponse toNoticeResponse(Notice notice);

    public NoticeForAdminResponse toNoticeListResponse(Notice notice);
}
