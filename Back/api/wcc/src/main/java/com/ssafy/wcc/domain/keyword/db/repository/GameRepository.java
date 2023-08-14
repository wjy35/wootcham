package com.ssafy.wcc.domain.keyword.db.repository;

import com.ssafy.wcc.domain.keyword.db.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
