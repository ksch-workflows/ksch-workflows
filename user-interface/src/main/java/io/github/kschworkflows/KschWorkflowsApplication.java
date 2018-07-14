package io.github.kschworkflows;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class KschWorkflowsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder().sources(KschWorkflowsApplication.class).run(args);
    }
}
