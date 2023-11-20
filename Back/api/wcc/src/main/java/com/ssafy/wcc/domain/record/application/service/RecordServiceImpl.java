package com.ssafy.wcc.domain.record.application.service;

import com.ssafy.wcc.domain.notice.application.service.NoticeServiceImpl;
import com.ssafy.wcc.domain.record.application.dto.response.RecordResponse;
import com.ssafy.wcc.domain.record.application.mapper.RecordMapper;
import com.ssafy.wcc.domain.record.db.entity.Record;
import com.ssafy.wcc.domain.record.db.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService{

    Logger logger = LoggerFactory.getLogger(RecordServiceImpl.class);

    private final RecordRepository recordRepository;
    private final RecordMapper recordMapper;

    @Override
    public List<RecordResponse> getRecord(Long memberId) {
        List<Record> recordList = recordRepository.findAllByMemberId(memberId);
        return recordList.stream()
                .map(n -> recordMapper.toRecordResponse(n))
                .collect(Collectors.toList());
    }

}
