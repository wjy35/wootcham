package com.ssafy.wcc.domain.keyword.application.mapper;

import com.ssafy.wcc.domain.keyword.application.dto.GameResponse;
import com.ssafy.wcc.domain.keyword.db.entity.Game;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GameMapper {
    GameMapper INSTANCE = Mappers.getMapper(GameMapper.class);

    public GameResponse toGameResponse(Game game);
}
