package com.po.pograph.controller;

import com.po.pograph.graph.Graph;
import com.po.pograph.operation.VertexNode;
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
public class VertexController {

    private final GraphService graphService;

    @Autowired
    public VertexController(GraphService graphService) {
        this.graphService = graphService;
    }

    @PutMapping(path = "/addnode", produces = APPLICATION_JSON_VALUE)
    public Graph addVertex(@RequestBody VertexNode nodes) {
        Graph graph = graphService.getReadGraph();

        ArrayList<Integer> nodeList =  graph.nodes.get(nodes.getNodeId());
        ArrayList<Integer> nodePrecursorList = graph.nodes.get(nodes.getPrecursorId());

        if (nodePrecursorList == null) {
            return graph;
        }
        if (nodeList != null) {
            //Node does already exists
            return graph;
        } else {
            graph.nodes.put(nodes.getNodeId(), new ArrayList<>());
        }
        nodePrecursorList.add(nodes.getNodeId());

        return graph;
    }

    @ResponseBody
    @PutMapping(path = "/removenode", produces = APPLICATION_JSON_VALUE)
    public Graph deleteVertex(@RequestBody VertexNode nodes) {
        Graph graph = graphService.getReadGraph();

        ArrayList<Integer> nodeList =  graph.nodes.get(nodes.getNodeId());

        if (nodeList == null) {
            //Node does not exist
            return graph;
        } else {
            if (nodeList.isEmpty()) {
                //nodeList does not have connection to different nodes.
                graph.nodes.remove(nodes.getNodeId());
                for (Map.Entry<Integer, ArrayList<Integer>> entry :graph.nodes.entrySet()) {
                    entry.getValue().remove(nodes.getNodeId());
                }
            }
        }
        return graph;
    }

    @ResponseBody
    @PutMapping(path = "/swapnodes", produces = APPLICATION_JSON_VALUE)
    public Graph swapNodes(@RequestBody VertexNode nodes) {
        return null;
    }

}
