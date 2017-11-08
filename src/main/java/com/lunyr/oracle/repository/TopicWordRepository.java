package com.lunyr.oracle.repository;

import com.lunyr.oracle.model.entity.TopicWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.Future;

@Repository
public interface TopicWordRepository extends JpaRepository<TopicWord, Long> {
    Optional<TopicWord> findByHash(String hash);
    @Async @Query("select t from TopicWord t where t.hash = ?1")
    Future<TopicWord> findByHashAsync(String hash);
}
