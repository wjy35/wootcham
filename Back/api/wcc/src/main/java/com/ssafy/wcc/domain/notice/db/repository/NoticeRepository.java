package com.ssafy.wcc.domain.notice.db.repository;

import com.ssafy.wcc.domain.notice.db.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Long> {
    Long countBy();
}
