package com.lunyr.oracle.repository;

import com.lunyr.oracle.model.entity.TopicWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicWordRepository extends JpaRepository<TopicWord, Long> {
    Optional<TopicWord> findByHash(String hash);
}
