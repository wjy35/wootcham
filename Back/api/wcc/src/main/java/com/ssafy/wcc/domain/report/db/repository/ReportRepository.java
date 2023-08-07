package com.ssafy.wcc.domain.report.db.repository;

import com.ssafy.wcc.domain.member.db.entity.MemberItem;
import com.ssafy.wcc.domain.report.db.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {

}
