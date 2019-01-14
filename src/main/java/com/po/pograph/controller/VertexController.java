package com.po.pograph.controller;

import com.po.pograph.graph.Graph;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/vertex")
public class VertexController {

    @ResponseBody
    @GetMapping(path = "/add", produces = APPLICATION_JSON_VALUE)
    public Graph addVertex(@RequestParam("node_id") int nodeId, @RequestParam("precursor_id") int precursorId) {
        return null;
    }

    @ResponseBody
    @DeleteMapping(path = "delete", produces = APPLICATION_JSON_VALUE)
    public Graph deleteVertex(@RequestParam("node_id") int nodeId) {
        return null;
    }

    @ResponseBody
    @PutMapping(path = "change", produces = APPLICATION_JSON_VALUE)
    public Graph swapNodes(@RequestParam("node1_id") int firstNodeId, @RequestParam("node1_id") int secondNodeId) {
        return null;
    }

}
