package com.po.pograph.graph;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Edge {

    public final Vertex target;
    public int weight;

    @Override
    public String toString() {
        return "Edge{" +
                "Vertex.id=" + target.getId() +
                ", weight=" + weight +
                '}';
    }

}
