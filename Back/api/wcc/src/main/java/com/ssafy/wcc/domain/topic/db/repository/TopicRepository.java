package com.ssafy.wcc.domain.topic.db.repository;

import com.ssafy.wcc.domain.topic.db.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Long> {
}
