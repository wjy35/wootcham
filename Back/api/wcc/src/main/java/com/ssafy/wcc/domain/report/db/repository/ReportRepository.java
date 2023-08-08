package com.ssafy.wcc.domain.report.db.repository;

import com.ssafy.wcc.domain.report.db.entity.Report;
import com.ssafy.wcc.domain.report.db.entity.ReportPK;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface ReportRepository extends JpaRepository<Report, Long> {

    Report findByReportPK_DateAndReportPK_MemberId(LocalDate date, Long memberId);

    Report findByReportPK(ReportPK reportPK);
}
