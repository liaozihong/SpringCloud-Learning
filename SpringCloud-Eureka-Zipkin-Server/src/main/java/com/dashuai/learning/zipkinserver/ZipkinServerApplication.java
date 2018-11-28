package com.dashuai.learning.zipkinserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Zipkin server application
 * <p/>
 * Created in 2018.11.28
 * <p/>
 *
 * @author Liaozihong
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class ZipkinServerApplication {

    /**
     * Hello string.
     *
     * @param name the name
     * @return the string
     */
    @RequestMapping("/hi")
    public String hello(String name){
        return "hello," + name;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ZipkinServerApplication.class, args);
    }
}
