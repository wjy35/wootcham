package com.ssafy.wcc.domain.notice.application.service;

import com.ssafy.wcc.common.exception.Error;
import com.ssafy.wcc.common.exception.WCCException;
import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.member.db.repository.MemberRepository;
import com.ssafy.wcc.domain.notice.application.dto.resonse.NoticeListResponse;
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
    public List<NoticeResponse> getNoticeListForUsers(long id) throws RuntimeException {
        logger.info("getNoticeListForUsers service 진입: {}", id);
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            logger.info("공지를 받으려는 Member 정보: {}", member);
            long count = noticeRepository.countBy();
            List<Notice> noticeList = noticeRepositorySupport.listNoticeForUsers(Math.min(count, 4));
            return noticeList.stream()
                    .map(n -> noticeMapper.toNoticeResponse(n))
                    .collect(Collectors.toList());
        }
        logger.info("!!!!!!!!");
        return null;
    }

    @Override
    public List<NoticeListResponse> getNoticeListForAdmin(long id) throws WCCException {
        logger.info("getNoticeListForAdmin service 진입");
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            if(member.get().getAdmin() != 1) throw new WCCException(Error.NOT_ADMIN);

            List<Notice> noticeList = noticeRepositorySupport.listNoticeForAdmin();
            return noticeList.stream()
                    .map(n -> noticeMapper.toNoticeListResponse(n))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
