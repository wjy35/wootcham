package com.ssafy.wcc.domain.member.application.dto.request;

import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberItemRequest {

    private boolean wear;

    private boolean buy;

    private int collectionId;

    private Long memberId;

    @Builder
    public MemberItemRequest(boolean wear, boolean buy, int collectionId, Long memberId) {
        this.wear = wear;
        this.buy = buy;
        this.collectionId = collectionId;
        this.memberId = memberId;
    }
}
