package com.lunyr.oracle.repository;

import com.lunyr.oracle.model.entity.EthereumLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EthereumLogsRepository extends JpaRepository<EthereumLogs, Long> {
}
