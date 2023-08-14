package com.ssafy.wcc.domain.record.application.service;

import com.ssafy.wcc.domain.member.db.entity.MemberItem;
import com.ssafy.wcc.domain.record.application.dto.response.RecordResponse;

import java.util.List;

public interface RecordService {
    List<RecordResponse> getRecord(Long id);
}
