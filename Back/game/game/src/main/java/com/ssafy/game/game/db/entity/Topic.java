package com.ssafy.game.game.db.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@IdClass(TopicPK.class)
public class Topic {
    @Id Integer type;
    @Id String keyword;
}
