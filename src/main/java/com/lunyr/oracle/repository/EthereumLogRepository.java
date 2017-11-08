package com.lunyr.oracle.repository;

import com.lunyr.oracle.model.entity.EthereumLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EthereumLogRepository extends JpaRepository<EthereumLog, Long> {
}
