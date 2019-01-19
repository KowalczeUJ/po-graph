package com.po.pograph.operation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class VertexNode {

    @JsonProperty("node_id")
    Integer nodeId;

    @JsonProperty("precursor_id")
    Integer precursorId;

}
