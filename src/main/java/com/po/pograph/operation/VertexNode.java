package com.po.pograph.operation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class VertexNode implements Serializable {

    @JsonProperty("node_id")
    Integer nodeId;

    @JsonProperty("precursor_id")
    Integer precursorId;

}
