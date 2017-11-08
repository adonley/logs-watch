package com.lunyr.oracle.model.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class EthereumLogHash extends BaseEntity {
    @Id
    @Column(name = "id", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="log_hash",  unique=true)
    private String logHash;

    @JsonManagedReference(value="hashLogsRef")
    @OneToMany(fetch=FetchType.EAGER, mappedBy="ethereumLogHash")
    private List<EthereumLogs> ethereumLogs = new ArrayList<>();
}
