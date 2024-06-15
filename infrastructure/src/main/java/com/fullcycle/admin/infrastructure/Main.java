package com.fullcycle.admin.infrastructure;

import com.fullcycle.admin.infrastructure.configuration.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.AbstractEnvironment;


@SpringBootApplication
@ComponentScan(basePackages = "com.fullcycle.admin")
public class Main {

    public static
    void main(String[] args) {
        System.out.println("Hello world?");
        System.setProperty(AbstractEnvironment.DEFAULT_PROFILES_PROPERTY_NAME, "test");
        SpringApplication.run(WebServerConfig.class, args);
    }
}