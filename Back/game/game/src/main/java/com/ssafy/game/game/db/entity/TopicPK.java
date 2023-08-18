package com.ssafy.game.game.db.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class TopicPK implements Serializable {
    private Integer type;
    private String keyword;
}
