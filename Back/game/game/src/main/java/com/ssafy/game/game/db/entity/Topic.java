package com.ssafy.game.game.db.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Getter
@NoArgsConstructor
@ToString
public class Topic {
    private Integer type;
    private String keyword;
    private Boolean useTopic;
    private Boolean displayTopic;

    public Topic(Integer type, String keyword) {
        this.type = type;
        this.keyword = keyword;
        this.useTopic = true;
        this.displayTopic = true;
    }

    void setTopic(Integer type, String keyword){
        this.type = type;
        this.keyword = keyword;
    }

    void setTopic(Integer type, String keyword, Boolean useTopic, Boolean displayTopic){
        this.displayTopic = displayTopic;
        this.useTopic = useTopic;
        this.keyword = keyword;
        this.type = type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
