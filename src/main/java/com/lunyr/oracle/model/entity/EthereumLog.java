package com.lunyr.oracle.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class EthereumLog extends BaseEntity {
    @Id
    @Column(name="id", nullable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name="data_log", joinColumns = {
            @JoinColumn(name="log_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name="data_id", referencedColumnName = "id")})
    private List<EthereumLogData> logData = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="topic_log", joinColumns = {
            @JoinColumn(name = "log_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "topic_id", referencedColumnName = "id")})
    private List<TopicWord> topicWords = new ArrayList<>();

    @Column(name="tx_hash")
    private String txHash;
}
