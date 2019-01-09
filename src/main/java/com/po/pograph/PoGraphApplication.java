package com.po.pograph;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class PoGraphApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(PoGraphApplication.class).run(args);
    }

}

