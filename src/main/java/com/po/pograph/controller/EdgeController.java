package com.po.pograph.controller;

import com.po.pograph.graph.Graph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.po.pograph.Service.GraphReadService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/edge")
public class EdgeController {

    @Autowired
    private GraphReadService graphReadService;

    @ResponseBody
    @GetMapping(path = "add", produces = APPLICATION_JSON_VALUE)
    public Graph addEdge() {
        return graphReadService.getReadGraph();
    }

    @ResponseBody
    @DeleteMapping(path = "/delete", produces = APPLICATION_JSON_VALUE)
    public Graph deleteEdge() {
        return null;
    }

    @ResponseBody
    @PatchMapping(path = "change", produces = APPLICATION_JSON_VALUE)
    public Graph changeEdgeValue() {
        return null;
    }
}
