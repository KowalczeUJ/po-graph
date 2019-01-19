package com.po.pograph.operation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EdgeNode {

    @JsonProperty("node1_id")
    Integer node1Id;

    @JsonProperty("node2_id")
    Integer node2Id;
}
