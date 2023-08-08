package com.ssafy.game.game.db.repository;

import com.ssafy.game.game.db.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Integer> {
    List<Topic> findAll();
}
