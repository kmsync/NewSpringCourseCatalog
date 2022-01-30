package com.bilgeadam.coursecatalog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaClient
public class NewCourseCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewCourseCatalogApplication.class, args);
    }

}
