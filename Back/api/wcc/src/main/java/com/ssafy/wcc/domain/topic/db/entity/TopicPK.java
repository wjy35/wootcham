package com.ssafy.wcc.domain.topic.db.entity;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
public class TopicPK implements Serializable {

    @Column(name = "type")
    private int type;

    @Column(name = "keyword")
    private String keyword;

}
