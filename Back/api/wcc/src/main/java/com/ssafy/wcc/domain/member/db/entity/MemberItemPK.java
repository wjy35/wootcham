package com.ssafy.wcc.domain.member.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class MemberItemPK implements Serializable {
    @Column(name="collection_id")
    private int collectionId;

    @Column(name="member_id")
    private Long memberId;
}