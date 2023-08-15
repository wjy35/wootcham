package com.ssafy.wcc.domain.topic.application.service;

import com.ssafy.wcc.domain.topic.application.dto.response.TopicTypeDetailResponse;
import com.ssafy.wcc.domain.topic.application.mapper.TopicMapper;
import com.ssafy.wcc.domain.topic.application.mapper.TopicTypeDetailMapper;
import com.ssafy.wcc.domain.topic.db.entity.Topic;
import com.ssafy.wcc.domain.topic.db.entity.TopicPK;
import com.ssafy.wcc.domain.topic.db.entity.TopicTypeDetail;
import com.ssafy.wcc.domain.topic.db.repository.TopicRepository;
import com.ssafy.wcc.domain.topic.db.repository.TopicTypeDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicServiceImpl implements TopicService {

    private final TopicTypeDetailRepository topicTypeDetailRepository;
    private final TopicRepository topicRepository;
    private final TopicTypeDetailMapper topicTypeDetailMapper;

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

    @Override
    public List<TopicTypeDetailResponse> getTopicTypeDetailList() {
        List<TopicTypeDetail> topicTypeDetails = topicTypeDetailRepository.findAll();
        return topicTypeDetails.stream()
                .map(n -> topicTypeDetailMapper.toTopicTypeDetailResponse(n))
                .collect(Collectors.toList());
    }

    @Override
    public List<Object> getTopicResponse(){
        List<Object> list = new ArrayList<>();

        list.add(this.getTopicList());
        list.add(this.getTopicTypeDetailList());

        return list;
    }
}


