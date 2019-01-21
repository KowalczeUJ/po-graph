package com.po.pograph.controller;

import com.po.pograph.graph.Graph;
import com.po.pograph.operation.EdgeNode;
import com.po.pograph.service.GraphService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class EdgeController {

    private final GraphService graphService;

    @Autowired
    public EdgeController(GraphService graphService) {
        this.graphService = graphService;
    }

    @ResponseBody
    @PutMapping(path = "/addlink", produces = APPLICATION_JSON_VALUE)
    public Graph addEdge(@RequestBody EdgeNode nodes) {
        Graph graph = graphService.getReadGraph();

        ArrayList<Integer> nodeList1 =  graph.nodes.get(nodes.getNode1Id());
        ArrayList<Integer> nodeList2 =  graph.nodes.get(nodes.getNode2Id());

        if (nodeList1 == null || nodeList2 == null) {
            //Node does not exist
            return graph;
        } else {
            if (!nodeList1.contains(nodes.getNode2Id()) && !nodeList2.contains(nodes.getNode1Id())) {
                //nodeList does not have connection to secondNodeId, and we could only one connection between nodes
                nodeList1.add(nodes.getNode2Id());
            }
        }
        return graph;
    }

    @ResponseBody
    @PutMapping(path = "/removelink", produces = APPLICATION_JSON_VALUE)
    public Graph deleteEdge(@RequestBody EdgeNode nodes) {
        Graph graph = graphService.getReadGraph();

        ArrayList<Integer> nodeList1 = graph.nodes.get(nodes.getNode1Id());
        ArrayList<Integer> nodeList2 = graph.nodes.get(nodes.getNode2Id());

        int referenceCounter = 0;

        for (Map.Entry<Integer, ArrayList<Integer>> entry : graph.nodes.entrySet()) {
            if (entry.getKey() != nodes.getNode1Id() && entry.getValue().contains(nodes.getNode2Id())) {
                referenceCounter++;
            }
        }

        //Node must has reference from another nodes.
        if (referenceCounter < 1) {
            return graph;
        }
        if (nodeList1 == null || nodeList2 == null) {
            //Node does not exist
            return graph;
        } else {
            if (nodeList1.contains(nodes.getNode2Id())) {
                //nodeList does have connection to secondNodeId.
                nodeList1.remove(nodes.getNode2Id());
            }
        }
        return graph;
    }

}
