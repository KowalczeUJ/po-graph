package com.po.pograph.service;

import com.po.pograph.graph.Graph;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

@Service
public class GraphReadService {

    @Getter
    private Graph readGraph;

    @PostConstruct
    public Graph init() {
        String workDirectory = System.getProperty("user.dir");
        readGraph =  this.loadGraph(workDirectory + "/src/main/resources/Input.txt");
        return readGraph;

    }

    private Graph loadGraph(String fileName) {
        Graph graph = null;

        int numberOfNodes = 0;
        String temp;

        try (Scanner in = new Scanner(new FileReader(fileName))) {
            while (in.hasNextLine()) {
                //Reading
                if (numberOfNodes <= 0) {
                    temp = in.nextLine();
                    numberOfNodes = Integer.parseInt(temp, 10);

                    if (numberOfNodes > 0) {
                        graph = new Graph(numberOfNodes);
                    } else {
                        return null;
                    }
                    continue;
                } else {
                    //TODO: Should be ERROR
                }

                //Reading the nodes connections
                for (int nodeIndex = 0; nodeIndex < numberOfNodes; nodeIndex++) {
                    if (in.hasNextLine()) {
                        temp = in.nextLine();
                        String[] stringNodes = temp.split(" ");
                        ArrayList<Integer> nodesIndex = new ArrayList<>();

                        for (String stringNode : stringNodes) {
                            Integer connection = Integer.parseInt(stringNode, 10);
                            nodesIndex.add(connection);

                        }

                        if (graph == null) {
                            throw new IllegalStateException("Graph is null, this means, that file input is incorrect");
                        } else {
                            graph.nodes.put(nodeIndex, nodesIndex);
                        }
                    } else {
                        throw new IllegalStateException("File input is incorrect");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return graph;
    }
}




