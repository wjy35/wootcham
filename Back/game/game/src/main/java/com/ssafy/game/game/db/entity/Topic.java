package com.ssafy.game.game.db.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@IdClass(TopicPK.class)
public class Topic {
    @Id Integer type;
    @Id String keyword;

    void setTopic(Integer type, String keyword){
        this.type = type;
        this.keyword = keyword;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
