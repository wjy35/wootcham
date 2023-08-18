package com.ssafy.wcc.domain.record.db.repository;

import com.ssafy.wcc.domain.record.db.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, Long> {
    List<Record> findAllByMemberId(Long memberId);
}
