package com.ssafy.wcc.domain.topic.db.entity;

import com.ssafy.wcc.domain.report.db.entity.Report;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "topic_type_detail")
public class TopicTypeDetail {

    @Id
    @Column(name = "type")
    private int type;

    @Column(name = "name")
    private String name;

    @Column(name = "sub_keyword")
    private int subKeyword;

    @OneToMany(mappedBy = "topicTypeDetail")
    private List<Topic> topicList = new ArrayList<>();
}
