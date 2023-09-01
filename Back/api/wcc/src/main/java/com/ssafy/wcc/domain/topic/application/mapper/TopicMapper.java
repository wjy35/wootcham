package com.ssafy.wcc.domain.topic.application.mapper;

import com.ssafy.wcc.domain.topic.application.dto.response.TopicResponse;
import com.ssafy.wcc.domain.topic.db.entity.Topic;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    TopicMapper INSTANCE = Mappers.getMapper(TopicMapper.class);

    public TopicResponse toTopicResponse(Topic topic);
}
