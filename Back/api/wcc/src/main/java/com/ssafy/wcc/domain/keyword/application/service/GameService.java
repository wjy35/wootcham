package com.ssafy.wcc.domain.keyword.application.service;


import com.ssafy.wcc.domain.keyword.application.dto.GameResponse;

import java.util.List;

public interface GameService {
    public List<GameResponse> getKeywordList();
}
