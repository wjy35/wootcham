package com.ssafy.wcc.domain.report.db.entity;

import com.ssafy.wcc.domain.member.db.entity.Member;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class ReportPK implements Serializable {
    private LocalDate date;

    @Column(name="member_id")
    private Long memberId;
}
