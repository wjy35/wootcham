package com.ssafy.wcc.domain.topic.application.service;


import com.ssafy.wcc.domain.topic.application.dto.response.TopicTypeDetailResponse;

import java.util.List;
import java.util.Map;

public interface TopicService {
    public Map<String, List<String>> getTopicList();

    public List<TopicTypeDetailResponse> getTopicTypeDetailList();

    public List<Object> getTopicResponse();
}
