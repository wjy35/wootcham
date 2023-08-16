package com.ssafy.game.game.db.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@DynamicUpdate
public class Member {
    @Id
    private Long id;

    @Column
    private String nickname;

    @Column
    private String point;
}
