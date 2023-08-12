package com.ssafy.wcc.domain.report.application.service;

import com.ssafy.wcc.domain.member.db.entity.Member;
import com.ssafy.wcc.domain.member.db.repository.MemberRepository;
import com.ssafy.wcc.domain.notice.presentation.NoticeController;
import com.ssafy.wcc.domain.report.application.dto.response.AllMemberResponse;
import com.ssafy.wcc.domain.report.db.entity.Report;
import com.ssafy.wcc.domain.report.db.entity.ReportPK;
import com.ssafy.wcc.domain.report.db.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{

    Logger logger = LoggerFactory.getLogger(NoticeController.class);

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    @Override
    public void report(Long memberId) {
        logger.info("report service 진입");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        String today = sdf.format(c.getTime());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(today, formatter);

        Optional<Report> report = reportRepository.findByReportPK_DateAndReportPK_MemberId(date, memberId);

        Optional<Member> member = memberRepository.findById(memberId);
        if(report.isPresent()){
            int count = report.get().getReport();
            Report newReport = Report.builder()
                    .reportPK(report.get().getReportPK())
                    .member(report.get().getMember())
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

    @Override
    public List<AllMemberResponse> getAllMemberList() {
        logger.info("getAllMemberList service 진입");
        List<Member> memberList = memberRepository.findAll();
        List<AllMemberResponse> allMemberResponsesList = new ArrayList<>();
        for(int i=0; i<memberList.size(); i++){
            Member member = memberList.get(i);

            List<Report> reportList = reportRepository.findAllByReportPK_MemberId(member.getId());

            int tot = 0;
            for(int j=0; j<reportList.size(); j++){
                tot += reportList.get(j).getReport();
            }
            AllMemberResponse allMemberResponse = AllMemberResponse.builder()
                    .id(member.getId())
                    .email(member.getEmail())
                    .nickname(member.getNickname())
                    .point(member.getPoint())
                    .admin(member.getAdmin())
                    .suspensionDay(member.getSuspensionDay())
                    .money(member.getMoney())
                    .reportCount(tot)
                    .build();

            allMemberResponsesList.add(allMemberResponse);
        }

        return allMemberResponsesList;
    }


}
