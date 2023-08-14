package com.ssafy.wcc.domain.keyword.application.service;

import com.ssafy.wcc.domain.keyword.application.dto.GameResponse;
import com.ssafy.wcc.domain.keyword.application.mapper.GameMapper;
import com.ssafy.wcc.domain.keyword.db.entity.Game;
import com.ssafy.wcc.domain.keyword.db.repository.GameRepository;
import com.ssafy.wcc.domain.notice.application.service.NoticeServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService{

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    @Override
    public List<GameResponse> getKeywordList() {
        List<Game> gameList = gameRepository.findAll();
        return gameList.stream()
                .map(n -> gameMapper.toGameResponse(n))
                .collect(Collectors.toList());
    }
}
