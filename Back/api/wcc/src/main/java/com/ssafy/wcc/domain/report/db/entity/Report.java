package com.ssafy.wcc.domain.report.db.entity;

import com.ssafy.wcc.domain.member.db.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class Report {
    @Column(name = "count")
    private int report;

    @EmbeddedId
    private ReportPK reportPK;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @MapsId("memberId")
    Member member;
}

