package com.ssafy.wcc.domain.notice.application.service;

import com.ssafy.wcc.common.exception.WCCException;
import com.ssafy.wcc.domain.notice.application.dto.resonse.NoticeForAdminResponse;
import com.ssafy.wcc.domain.notice.application.dto.resonse.NoticeResponse;
import com.ssafy.wcc.domain.notice.db.entity.Notice;

import java.util.List;

public interface NoticeService {

    public List<NoticeResponse> getNoticeListForUsers(long id) throws WCCException;

    public List<NoticeForAdminResponse> getNoticeListForAdmin(long id) throws WCCException;

    public NoticeResponse getNoticeDetail(long memberId, long noticeId) throws WCCException;
}
