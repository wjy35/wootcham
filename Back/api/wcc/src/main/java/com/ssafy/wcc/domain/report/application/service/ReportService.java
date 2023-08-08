package com.ssafy.wcc.domain.report.application.service;

import com.ssafy.wcc.domain.report.application.dto.response.AllMemberResponse;

import java.util.List;

public interface ReportService {
    void report(Long memberId);

    List<AllMemberResponse> getList();
}
