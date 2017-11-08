package com.lunyr.oracle.repository;

import com.lunyr.oracle.model.entity.TopicWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicWordRepository extends JpaRepository<TopicWord, Long> {
}
