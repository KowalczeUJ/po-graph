package com.po.pograph.controller;

import com.po.pograph.graph.Graph;
import com.po.pograph.service.GraphReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class EdgeController {

    private final GraphReadService graphReadService;

    @Autowired
    public EdgeController(GraphReadService graphReadService) {
        this.graphReadService = graphReadService;
    }

    @ResponseBody
    @PutMapping(path = "/addlink", produces = APPLICATION_JSON_VALUE)
    public Graph addEdge(@RequestParam("node1_id") int firstNodeId, @RequestParam("node2_id") int secondNodeId) {
        Graph graph = graphReadService.getReadGraph();

        ArrayList<Integer> nodeList1 =  graph.nodes.get(firstNodeId);
        ArrayList<Integer> nodeList2 =  graph.nodes.get(secondNodeId);

        if (nodeList1 == null || nodeList2 == null) {
            //Node does not exist
            return graph;
        } else {
            if (!nodeList1.contains(secondNodeId) && !nodeList2.contains(firstNodeId)) {
                //nodeList does not have connection to secondNodeId, and we could only one connection between nodes
                nodeList1.add(secondNodeId);
            }
        }
        return graph;
    }

    @ResponseBody
    @PutMapping(path = "/removelink", produces = APPLICATION_JSON_VALUE)
    public Graph deleteEdge(@RequestParam("node1_id") int firstNodeId, @RequestParam("node2_id") int secondNodeId) {

        Graph graph = graphReadService.getReadGraph();

        ArrayList<Integer> nodeList1 = graph.nodes.get(firstNodeId);
        ArrayList<Integer> nodeList2 = graph.nodes.get(secondNodeId);

        int referenceCounter = 0;

        for (Map.Entry<Integer, ArrayList<Integer>> entry : graph.nodes.entrySet()) {
            if (entry.getKey() != firstNodeId && entry.getValue().contains(secondNodeId)) {
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
            if (nodeList1.contains(secondNodeId)) {
                //nodeList does have connection to secondNodeId.
                nodeList1.remove(secondNodeId);
            }
        }
        return graph;
    }

}
