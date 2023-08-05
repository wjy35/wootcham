package com.ssafy.wcc.domain.member.db.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MemberItemPK implements Serializable {
    @Column(name="collection_id")
    private int collectionId;

    @Column(name="member_id")
    private Long memberId;
}