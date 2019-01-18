package com.po.pograph.controller;

import com.po.pograph.graph.Graph;
import com.po.pograph.service.GraphReadService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

@RestController
@Log4j2
public class MainController {

    private final HttpServletRequest request;
    private final GraphReadService graphReadService;

    @Autowired
    public MainController(HttpServletRequest request, GraphReadService graphReadService) {
        this.request = request;
        this.graphReadService = graphReadService;
    }

    @ResponseBody
    @PostMapping(path = "/file")
    public Graph getFile(@RequestParam("file") MultipartFile file) {
        try {
            String uploadDir = "/resources/";
            String realPath = request.getServletContext().getRealPath(uploadDir);

            File transferFile = new File(realPath + "/" + file.getOriginalFilename());
            file.transferTo(transferFile);
        } catch (Exception e) {
            log.error("Error while uploading file to the server.");
        }
        graphReadService.loadGraph(file.getOriginalFilename());
        return graphReadService.getReadGraph();
    }

}
