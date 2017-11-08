package com.lunyr.oracle.repository;

import com.lunyr.oracle.model.entity.EthereumLogData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.Future;

@Repository
public interface EthereumLogDataRepository extends JpaRepository<EthereumLogData, Long> {
    Optional<EthereumLogData> findByHexData(String hexData);
    @Async @Query("select t from EthereumLogData t where t.hexData = ?1")
    Future<EthereumLogData> findByHexDataAsync(String hexData);
}
