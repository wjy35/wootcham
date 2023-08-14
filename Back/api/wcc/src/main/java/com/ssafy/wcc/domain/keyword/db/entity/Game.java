package com.ssafy.wcc.domain.keyword.db.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@RequiredArgsConstructor
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int type;
    private String keyword;

}
