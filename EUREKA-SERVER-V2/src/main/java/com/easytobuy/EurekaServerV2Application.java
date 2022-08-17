package com.easytobuy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerV2Application {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerV2Application.class, args);
    }

}
