package com.ssafy.wcc.domain.rank.application.service;

import com.ssafy.wcc.domain.member.application.dto.response.MemberInfoResponse;
import com.ssafy.wcc.domain.member.db.entity.Member;

import java.util.ArrayList;
import java.util.List;

public interface RankService {
    public List<MemberInfoResponse> getRank();
}
