package com.ssafy.wcc.domain.record.db.entity;

import com.ssafy.wcc.domain.member.db.entity.Member;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Record {

    @Column(name = "smile_count")
    private int smileCount;

    @Column(name = "change_point")
    private int changePoint;

    @Column(name = "change_money")
    private int changeMoney;

    private Date end;

    private Date start;

    private int rankPoint;

    @EmbeddedId
    private RecordPK recordPK;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @MapsId("memberId")
    Member member;
}
