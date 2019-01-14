package com.po.pograph.controller;

import com.po.pograph.graph.Graph;
import com.po.pograph.service.GraphReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/vertex")
public class VertexController {

    private final GraphReadService graphReadService;

    @Autowired
    public VertexController(GraphReadService graphReadService) {
        this.graphReadService = graphReadService;
    }

    @ResponseBody
    @GetMapping(path = "/add", produces = APPLICATION_JSON_VALUE)
    public Graph addVertex(@RequestParam("node_id") int nodeId, @RequestParam("precursor_id") int precursorId) {
        Graph graph = graphReadService.getReadGraph();

        ArrayList<Integer> nodeList =  graph.nodes.get(nodeId);
        ArrayList<Integer> nodePrecursorList = graph.nodes.get(precursorId);

        if (nodePrecursorList == null) {
            return graph;
        }
        if (nodeList != null) {
            //Node does already exists
            return graph;
        } else {
            graph.nodes.put(nodeId, new ArrayList<>());
        }
        nodePrecursorList.add(nodeId);

        return graph;
    }

    @ResponseBody
    @DeleteMapping(path = "/delete", produces = APPLICATION_JSON_VALUE)
    public Graph deleteVertex(@RequestParam("node_id") int nodeId) {
        Graph graph = graphReadService.getReadGraph();

        ArrayList<Integer> nodeList =  graph.nodes.get(nodeId);

        if (nodeList == null) {
            //Node does not exist
            return graph;
        } else {
            if (nodeList.isEmpty()) {
                //nodeList does not have connection to different nodes.
                graph.nodes.remove(nodeId);
            }
        }
        return graph;
    }

    @ResponseBody
    @PutMapping(path = "/swap", produces = APPLICATION_JSON_VALUE)
    public Graph swapNodes(@RequestParam("node1_id") int firstNodeId, @RequestParam("node1_id") int secondNodeId) {
        return null;
    }

}
