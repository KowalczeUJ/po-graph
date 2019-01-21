package com.po.pograph.controller;

import com.po.pograph.graph.Graph;
import com.po.pograph.service.GraphService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@Log4j2
public class MainController {

    private final GraphService graphService;

    @Autowired
    public MainController(GraphService graphService) {
        this.graphService = graphService;
    }

    @ResponseBody
    @PostMapping(path = "/file", produces = APPLICATION_JSON_VALUE)
    public Graph getFile(@RequestParam("file") MultipartFile file) {
        File transferFile = null;
        try {
            String workDirectory = System.getProperty("user.dir") + "/src/main/resources/";
            String newFileName = Objects.requireNonNull(file.getOriginalFilename()).replace(".", "1.");
            String path = workDirectory + newFileName;

            transferFile = new File(path);
            file.transferTo(transferFile);
        } catch (Exception e) {
            log.error("Error while uploading file to the server.");
        }
        graphService.loadGraph(transferFile.getName());
        return graphService.getReadGraph();
    }

}
