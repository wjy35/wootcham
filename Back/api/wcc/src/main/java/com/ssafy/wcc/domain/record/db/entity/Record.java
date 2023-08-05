package com.ssafy.wcc.domain.record.db.entity;

import com.ssafy.wcc.domain.member.db.entity.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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

    private LocalDate end;

    private int rankPoint;

    @EmbeddedId
    private RecordKey recordKey;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    @MapsId("memberId")
    Member member;
}
