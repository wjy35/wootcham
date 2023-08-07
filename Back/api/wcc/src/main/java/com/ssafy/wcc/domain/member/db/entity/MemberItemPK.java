package com.ssafy.wcc.domain.member.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@RequiredArgsConstructor
public class MemberItemPK implements Serializable {
    @Column(name="collectionId")
    private Long collectionId;

    @Column(name="member_id")
    private Long memberId;

    @Builder
    public MemberItemPK(Long collectionId, Long memberId) {
        this.collectionId = collectionId;
        this.memberId = memberId;
    }
}