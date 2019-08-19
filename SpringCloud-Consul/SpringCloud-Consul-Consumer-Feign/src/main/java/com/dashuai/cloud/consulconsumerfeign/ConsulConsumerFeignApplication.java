package com.dashuai.cloud.consulconsumerfeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * Consul consumer feign application
 * <p/>
 * Created in 2018.08.07
 * <p/>
 *
 * @author Liaozihong
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCircuitBreaker
@EnableHystrix
public class ConsulConsumerFeignApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ConsulConsumerFeignApplication.class, args);
    }
}
