package com.dashuai.cloud.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@EnableHystrix
@EnableCircuitBreaker
@EnableTurbine
@EnableHystrixDashboard
public class ConsulProducerApplication {

    @Value("${server.port}")
    private String port;

    @RequestMapping("/hi")
    public String hi(String name){
        return new StringBuilder().append("Hello world! ").append(name).
                append("，你的端口为").append(port).toString();
    }
    public static void main(String[] args) {
        SpringApplication.run(ConsulProducerApplication.class, args);
    }

    @RequestMapping("/health")
    public String health(){
        return new Date().toString();
    }
}
