package com.lunyr.oracle.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper=false)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class EthereumLog extends BaseEntity {
    @Id
    @Column(name="id", nullable=false, updatable=false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="data_log", joinColumns = {
            @JoinColumn(name="log_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name="data_id", referencedColumnName = "id")})
    private List<EthereumLogData> logData = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="topic_log", joinColumns = {
            @JoinColumn(name = "log_id", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "topic_id", referencedColumnName = "id")})
    private Set<TopicWord> topicWords = new HashSet<>();

    @Column(name="tx_hash")
    private String txHash;
}
