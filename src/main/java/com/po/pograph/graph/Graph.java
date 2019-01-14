package com.po.pograph.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph {

    private final int numberOfNodes;
    
    public Graph(int number)
    {
        this.numberOfNodes = number;
    }

    public Map<Integer, ArrayList<Integer>> nodes = new HashMap<>();

    @Override
    public String toString() {
        return nodes.toString();
    }

}
