package com.dashuai.learning.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Service application
 * <p/>
 * Created in 2018.11.28
 * <p/>
 *
 * @author Liaozihong
 */
@SpringBootApplication
@EnableEurekaServer
public class ServerApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
