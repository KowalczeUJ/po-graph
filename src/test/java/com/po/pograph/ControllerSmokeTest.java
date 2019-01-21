package com.po.pograph;

import com.po.pograph.controller.EdgeController;
import com.po.pograph.controller.MainController;
import com.po.pograph.controller.VertexController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerSmokeTest {

    @Autowired
    private EdgeController edgeController;

    @Autowired
    private MainController mainController;

    @Autowired
    private VertexController vertexController;

    @Test
    public void contextLoads() {
        assertThat(edgeController).isNotNull();
        assertThat(mainController).isNotNull();
        assertThat(vertexController).isNotNull();
    }

}
