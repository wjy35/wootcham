package com.ssafy.wcc.domain.topic.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "topic")
public class Topic {

    @EmbeddedId
    private TopicPK topicPK;

    @Builder
    public Topic(TopicPK topicPK) {
        this.topicPK = topicPK;
    }
}
