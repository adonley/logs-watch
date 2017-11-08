package com.lunyr.oracle.model.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class EthereumLogDTO {
    private String txhash;
    private List<String> topics;
    private List<String> data;
}
