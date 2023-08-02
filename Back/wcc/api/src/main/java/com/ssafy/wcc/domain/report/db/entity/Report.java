package com.ssafy.wcc.domain.report.db.entity;

import com.ssafy.wcc.domain.member.db.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Report {
    @Column(name = "count")
    private int report;

    @EmbeddedId
    private ReportPK reportPK;

    @ManyToOne
    Member member;
}
