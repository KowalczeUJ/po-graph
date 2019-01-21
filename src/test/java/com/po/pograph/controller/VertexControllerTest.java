package com.po.pograph.controller;

import com.po.pograph.graph.Graph;
import com.po.pograph.operation.VertexNode;
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
@WebMvcTest(VertexController.class)
public class VertexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GraphService service;

    @Shared
    private Graph graph;

    @Before
    public void setup() {
        graph = new Graph(4);
        graph.nodes = new HashMap<Integer, ArrayList<Integer>>() {{
            put(0, newArrayList(3, 1, 2));
            put(1, newArrayList(0, 2));
            put(2, newArrayList(0, 1, 3));
            put(3, newArrayList());
        }};
    }

    @Test
    public void shouldAddGraphNode() throws Exception {
        //given
        VertexNode vertexNode = new VertexNode();
        vertexNode.setNodeId(4);
        vertexNode.setPrecursorId(3);

        //when
        when(service.getReadGraph()).thenReturn(graph);

        //then
        this.mockMvc.perform(put("/addnode")
                .contentType(APPLICATION_JSON)
                .content(convertObjectToJsonBytes(vertexNode)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("{'nodes':{'0':[3,1,2],'1':[0,2],'2':[0,1,3],'3':[4], '4':[]}}"));
    }

    @Test
    public void shouldRemoveGraphNode() throws Exception {
        //given
        VertexNode vertexNode = new VertexNode();
        vertexNode.setNodeId(3);

        //when
        when(service.getReadGraph()).thenReturn(graph);

        //then
        this.mockMvc.perform(put("/removenode")
                .contentType(APPLICATION_JSON)
                .content(convertObjectToJsonBytes(vertexNode)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().json("{'nodes':{'0':[1,2],'1':[0,2],'2':[0,1]}}"));
    }

}