package com.lunyr.oracle.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@Data
public class EthereumLogs extends BaseEntity {
    @Id
    @Column(name="id", nullable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonBackReference(value="logsHashRef")
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="hash_id", referencedColumnName="id")
    private EthereumLogHash ethereumLogHash;

    @Column(name="created")
    private Timestamp created;
}
