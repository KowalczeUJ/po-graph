package com.po.pograph.controller;

import com.po.pograph.Service.GraphReadService;
import com.po.pograph.graph.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/edge")
public class EdgeController {

    @Autowired
    private GraphReadService graphReadService;

    @ResponseBody
    @GetMapping(path = "add", produces = APPLICATION_JSON_VALUE)
    public Graph addEdge(@RequestParam("node1_id") int node1_id, @RequestParam("node2_id") int node2_id) {

        Graph graph = graphReadService.getReadGraph();

        ArrayList<Integer> nodeList =  graph.nodes.get(node1_id);

        if (nodeList == null) {
            //Node does not exist
            return graph;

        } else {
            if (!nodeList.contains(node2_id)) {
                //nodeList does not have connection to node2_id.
                nodeList.add(node2_id);
            }
        }

        return graph;

    }

    @ResponseBody
    @DeleteMapping(path = "/delete", produces = APPLICATION_JSON_VALUE)
    public Graph deleteEdge(@RequestParam("node1_id") int node1_id, @RequestParam("node2_id") int node2_id) {

        Graph graph = graphReadService.getReadGraph();

        ArrayList<Integer> nodeList =  graph.nodes.get(node1_id);

        if (nodeList == null) {
            //Node does not exist
            return graph;

        } else {
            if (nodeList.contains(node2_id)) {
                //nodeList does have connection to node2_id.
                nodeList.remove(node2_id);
            }
        }

        return graph;
    }

}
