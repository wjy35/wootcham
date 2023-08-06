package com.ssafy.wcc.domain.notice.application.service;

import com.ssafy.wcc.domain.notice.application.dto.resonse.NoticeResponse;

import java.util.List;

public interface NoticeService {

    public List<NoticeResponse> getNoticeListForUsers(Long id) throws RuntimeException;
}
