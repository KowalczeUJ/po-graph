package com.po.pograph.controller;

import com.po.pograph.domain.Graph;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/edge")
public class EdgeController {

    @ResponseBody
    @GetMapping(path = "add", produces = APPLICATION_JSON_VALUE)
    public Graph addEdge() {
        return null;
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
