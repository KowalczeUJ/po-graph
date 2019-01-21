package com.po.pograph.operation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class EdgeNode implements Serializable {

    @JsonProperty("node1_id")
    Integer node1Id;

    @JsonProperty("node2_id")
    Integer node2Id;
}
