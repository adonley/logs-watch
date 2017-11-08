package com.lunyr.oracle.repository;

import com.lunyr.oracle.model.entity.EthereumLogData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EthereumLogDataRepository extends JpaRepository<EthereumLogData, Long> {
    Optional<EthereumLogData> findByHexData(String hexData);
}
