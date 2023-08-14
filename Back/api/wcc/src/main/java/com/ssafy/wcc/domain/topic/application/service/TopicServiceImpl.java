package com.ssafy.wcc.domain.topic.application.service;

import com.ssafy.wcc.domain.topic.application.mapper.TopicMapper;
import com.ssafy.wcc.domain.topic.db.entity.Topic;
import com.ssafy.wcc.domain.topic.db.entity.TopicPK;
import com.ssafy.wcc.domain.topic.db.repository.TopicRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicRepository topicRepository;
    private final TopicMapper topicMapper;

    Logger logger = LoggerFactory.getLogger(TopicServiceImpl.class);

    @Override
    public Map<String, List<String>> getTopicList() {
        List<Topic> topicList = topicRepository.findAll();
        Map<String, List<String>> resultMap = topicList.stream()
                .collect(Collectors.groupingBy(
                        topic -> String.valueOf(topic.getTopicPK().getType()),
                        Collectors.mapping(topic -> topic.getTopicPK().getKeyword(), Collectors.toList())
                ));
        return resultMap;
    }
}
