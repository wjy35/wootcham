package com.ssafy.wcc.domain.report.application.service;

import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.member.db.repository.MemberRepository;
import com.ssafy.wcc.domain.report.db.entity.Report;
import com.ssafy.wcc.domain.report.db.entity.ReportPK;
import com.ssafy.wcc.domain.report.db.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    @Override
    public void report(Long memberId) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String today = sdf.format(c.getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(today, formatter);

        Report report = reportRepository.findByReportPK_DateAndReportPK_MemberId(date, memberId);

        Optional<Member> member = memberRepository.findById(memberId);
        if(report != null){
            int count = report.getReport();
            Report newReport = Report.builder()
                    .reportPK(report.getReportPK())
                    .member(report.getMember())
                    .report(count+1).build();
            reportRepository.save(newReport);
        }else{
            ReportPK reportPK = ReportPK.builder().memberId(memberId).date(LocalDate.parse(today)).build();

            Report newReport = Report.builder()
                    .reportPK(reportPK)
                    .member(member.get())
                    .report(1).build();
            reportRepository.save(newReport);
        }
    }
}
