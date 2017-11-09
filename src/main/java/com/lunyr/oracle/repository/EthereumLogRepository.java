package com.lunyr.oracle.repository;

import com.lunyr.oracle.model.entity.EthereumLog;
import com.lunyr.oracle.model.entity.TopicWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EthereumLogRepository extends JpaRepository<EthereumLog, Long> {
    @Query(value = "select el.* from ethereum_log el inner join topic_log tl on el.id = tl.log_id inner join topic_word tw on tl.topic_id = tw.id where tw.hash_str = ?1", nativeQuery = true)
    List<EthereumLog> findByTopic(TopicWord t);
}
