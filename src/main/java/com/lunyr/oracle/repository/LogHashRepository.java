package com.lunyr.oracle.repository;

import com.lunyr.oracle.model.entity.EthereumLogHash;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogHashRepository extends JpaRepository<EthereumLogHash, Long> {
    Optional<EthereumLogHash> findByLogHash(String logHash);
}
