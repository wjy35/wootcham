package com.ssafy.wcc.domain.report.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
public class ReportPK implements Serializable {
    private LocalDate date;

    @Column(name = "member_id")
    private Long memberId;
}
