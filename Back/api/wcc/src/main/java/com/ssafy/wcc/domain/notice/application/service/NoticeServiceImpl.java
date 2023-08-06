package com.ssafy.wcc.domain.notice.application.service;

import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.member.db.repository.MemberRepository;
import com.ssafy.wcc.domain.notice.application.dto.resonse.NoticeResponse;
import com.ssafy.wcc.domain.notice.application.mapper.NoticeMapper;
import com.ssafy.wcc.domain.notice.db.entity.Notice;
import com.ssafy.wcc.domain.notice.db.repository.NoticeRepositorySupport;
import com.ssafy.wcc.domain.notice.db.repository.NoticeRepository;
import com.ssafy.wcc.domain.notice.presentation.NoticeController;
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

    private MemberRepository memberRepository;
    private NoticeRepository noticeRepository;
    private NoticeRepositorySupport noticeRepositorySupport;
    private NoticeMapper noticeMapper;

    @Override
    public List<NoticeResponse> getNoticeListForUsers(Long id) throws RuntimeException {
        log.info("getNoticeListForUsers service 진입: {}", id);
        Optional<Member> member = memberRepository.findById(id);
        if (member.isPresent()) {
            log.info("공지를 받으려는 Member 정보: {}", member);
            long count = noticeRepository.countBy();
            List<Notice> noticeList = noticeRepositorySupport.listNoticeForUsers(Math.min(count, 4));
            return noticeList.stream()
                    .map(n -> noticeMapper.toNoticeResponse(n))
                    .collect(Collectors.toList());
        }
        log.info("!!!!!!!!");
        return null;
    }
}
