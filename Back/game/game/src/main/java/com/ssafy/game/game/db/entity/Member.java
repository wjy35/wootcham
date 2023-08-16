package com.ssafy.game.game.db.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Member {
    @Id
    private String nickname;

    @Column
    private String point;
}
