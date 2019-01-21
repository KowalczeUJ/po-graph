package com.po.pograph.controller;

import com.po.pograph.graph.Graph;
import com.po.pograph.operation.EdgeNode;
import com.po.pograph.service.GraphService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import spock.lang.Shared;

import java.util.ArrayList;
import java.util.HashMap;

import static com.google.common.collect.Lists.newArrayList;
import static com.po.pograph.util.IntegrationTestUtils.convertObjectToJsonBytes;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EdgeController.class)
public class EdgeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GraphService service;

    @Shared
    private Graph graph;

    @Before
    public void setup() {
        graph = new Graph(5);
        graph.nodes = new HashMap<Integer, ArrayList<Integer>>() {{
            put(0, newArrayList(3, 1, 2));
            put(1, newArrayList(0, 2));
            put(2, newArrayList(0, 1, 3, 4));
            put(3, newArrayList(0, 2, 4));
            put(4, newArrayList(3, 2));
        }};
    }

    @Test
    public void shouldAddGraphLink() throws Exception {
        //given
        EdgeNode edgeNode = new EdgeNode();
        edgeNode.setNode1Id(1);
        edgeNode.setNode2Id(3);

        //when
        when(service.getReadGraph()).thenReturn(graph);

        //then
        this.mockMvc.perform(put("/addlink")
                .contentType(APPLICATION_JSON)
                .content(convertObjectToJsonBytes(edgeNode)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("{'nodes':{'0':[3,1,2],'1':[0,2,3],'2':[0,1,3,4],'3':[0,2,4],'4':[3,2]}}"));
    }

    @Test
    public void shouldRemoveGraphLink() throws Exception {
        //given
        EdgeNode edgeNode = new EdgeNode();
        edgeNode.setNode1Id(4);
        edgeNode.setNode2Id(2);

        //when
        when(service.getReadGraph()).thenReturn(graph);

        //then
        this.mockMvc.perform(put("/removelink")
                .contentType(APPLICATION_JSON)
                .content(convertObjectToJsonBytes(edgeNode)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("{'nodes':{'0':[3,1,2],'1':[0,2],'2':[0,1,3,4],'3':[0,2,4],'4':[3]}}"));
    }

}