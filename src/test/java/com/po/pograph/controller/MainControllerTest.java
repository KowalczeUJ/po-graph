package com.po.pograph.controller;

import com.po.pograph.graph.Graph;
import com.po.pograph.service.GraphService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

import static com.google.common.collect.Lists.newArrayList;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MainController.class)
public class MainControllerTest {

    @MockBean
    private GraphService graphService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test() throws Exception {
        //given
        File file = new File(getFilePath("Input.txt"));
        MockMultipartFile inputFile = new MockMultipartFile("file", "Input.txt", "text/plain", Files.readAllBytes(file.toPath()));

        Graph graph = new Graph(5);
        graph.nodes = new HashMap<Integer, ArrayList<Integer>>() {{
            put(0, newArrayList(3, 1, 2));
            put(1, newArrayList(0, 2));
            put(2, newArrayList(0, 1, 3, 4));
            put(3, newArrayList(0, 2, 4));
            put(4, newArrayList(3, 2));
        }};

        //when
        when(graphService.getReadGraph()).thenReturn(graph);

        //then
        mockMvc.perform(MockMvcRequestBuilders.multipart("/file")
                .file(inputFile))
                .andExpect(status().is(200))
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("{'nodes':{'0':[3,1,2],'1':[0,2],'2':[0,1,3,4],'3':[0,2,4],'4':[3, 2]}}"));

        File uploadedFile = new File(getFilePath("Input1.txt"));

        assertTrue(uploadedFile.exists());
        uploadedFile.delete();
    }

    private String getFilePath(String filename) {
        String workDirectory = System.getProperty("user.dir");
        return workDirectory + "/src/main/resources/" + filename;
    }

}