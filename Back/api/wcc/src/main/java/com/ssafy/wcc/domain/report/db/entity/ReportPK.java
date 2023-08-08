package com.ssafy.wcc.domain.report.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Embeddable
@RequiredArgsConstructor
public class ReportPK implements Serializable {

    @Column(name = "date")
    private LocalDate date;

    @Column(name="member_id")
    private Long memberId;


    @Builder
    public ReportPK(LocalDate date, Long memberId) {
        this.date = date;
        this.memberId = memberId;
    }
}
