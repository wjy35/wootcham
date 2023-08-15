package com.ssafy.wcc.domain.topic.application.dto.response;

import com.ssafy.wcc.domain.topic.db.entity.TopicPK;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TopicResponse {
    private int type;
    private String keyword;

    @Builder
    public TopicResponse(TopicPK topicPK) {
        this.type = topicPK.getType();
        this.keyword = topicPK.getKeyword();
    }
}
