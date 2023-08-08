package com.ssafy.wcc.domain.notice.application.service;

import com.ssafy.wcc.common.exception.Error;
import com.ssafy.wcc.common.exception.WCCException;
import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.member.db.repository.MemberRepository;
import com.ssafy.wcc.domain.notice.application.dto.request.NoticeRequest;
import com.ssafy.wcc.domain.notice.application.dto.resonse.NoticeForAdminResponse;
import com.ssafy.wcc.domain.notice.application.dto.resonse.NoticeResponse;
import com.ssafy.wcc.domain.notice.application.mapper.NoticeMapper;
import com.ssafy.wcc.domain.notice.db.entity.Notice;
import com.ssafy.wcc.domain.notice.db.repository.NoticeRepositorySupport;
import com.ssafy.wcc.domain.notice.db.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    Logger logger = LoggerFactory.getLogger(NoticeServiceImpl.class);

    private final MemberRepository memberRepository;
    private final NoticeRepository noticeRepository;
    private final NoticeRepositorySupport noticeRepositorySupport;
    private final NoticeMapper noticeMapper;

    @Override
    public List<NoticeResponse> getNoticeListForUsers(long id) throws WCCException {
        logger.info("getNoticeListForUsers service 진입: {}", id);
        checkMember(id, 1);
        
        long count = noticeRepository.countBy();
        List<Notice> noticeList = noticeRepositorySupport.listNoticeForUsers(Math.min(count, 4));
        return noticeList.stream()
                .map(n -> noticeMapper.toNoticeResponse(n))
                .collect(Collectors.toList());
    }

    @Override
    public List<NoticeForAdminResponse> getNoticeListForAdmin(long id) throws WCCException {
        logger.info("getNoticeListForAdmin service 진입");
        checkMember(id, 0);

        List<Notice> noticeList = noticeRepositorySupport.listNoticeForAdmin();
        return noticeList.stream()
                .map(n -> noticeMapper.toNoticeListResponse(n))
                .collect(Collectors.toList());
    }

    @Override
    public NoticeResponse getNoticeDetail(long memberId, long noticeId) throws WCCException {
        logger.info("getNoticeDetail service 진입");
        checkMember(memberId, 0);

        Optional<Notice> notice = noticeRepositorySupport.getNoticeDetail(noticeId);
        if (notice.isEmpty()) throw new WCCException(Error.NO_SUCH_NOTICE);
        return noticeMapper.toNoticeResponse(notice.get());
    }

    @Override
    @Transactional
    public void registerNotice(long id, NoticeRequest noticeRequest) throws WCCException {
        logger.info("registerNotice service 진입");
        checkMember(id, 0);

        Notice notice = Notice.builder()
                .subject(noticeRequest.getSubject())
                .content(noticeRequest.getContent())
                .date(new Date())
                .build();
        noticeRepository.save(notice);
    }

    @Override
    @Transactional
    public void updateNotice(long id, NoticeRequest noticeRequest) throws WCCException {
        logger.info("updateNotice service 진입");
        checkMember(id, 0);

        noticeRepositorySupport.updateNoticeInfo(noticeRequest);
    }

    public void checkMember(long id, int type) throws WCCException {
        Optional<Member> member = memberRepository.findById(id);
        if (member.isEmpty()) throw new WCCException(Error.USER_NOT_FOUND);

        // 관리자인지도 추가로 확인해야하는 경우
        if (type == 0) if (member.get().getAdmin() != 0) throw new WCCException(Error.NOT_ADMIN);

        logger.info("공지를 받으려는 Member 이메일: {}", member.get().getEmail());
    }

}
