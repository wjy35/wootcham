package com.ssafy.wcc.domain.record.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RecordPK implements Serializable {
    @Column(name="record_id")
    private Long recordId;

    @Column(name="member_id")
    private Long memberId;
}
