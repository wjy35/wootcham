package com.ssafy.wcc.domain.notice.application.service;

import com.ssafy.wcc.common.exception.WCCException;
import com.ssafy.wcc.domain.notice.application.dto.resonse.NoticeListResponse;
import com.ssafy.wcc.domain.notice.application.dto.resonse.NoticeResponse;

import java.util.List;

public interface NoticeService {

    public List<NoticeResponse> getNoticeListForUsers(long id) throws RuntimeException;

    public List<NoticeListResponse> getNoticeListForAdmin(long id) throws WCCException;
}
