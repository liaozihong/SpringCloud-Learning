package com.dashuai.learning.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Gateway service application
 * <p/>
 * Created in 2018.11.28
 * <p/>
 * 配置服务经过Zuul在记录到Zipkin 上，可追踪调用记录链
 *
 * @author Liaozihong
 */
@RestController
@SpringBootApplication
@EnableHystrix
//配置服务注册与发现，http://网关地址/服务/API?params ,转发至特定服务，可在yml配置调整，for example： http://localhost:5000/service-hi/hi?name=1da
@EnableDiscoveryClient
public class GatewayServiceApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }


    /**
     * Fallback mono.
     *
     * @return the mono
     */
    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }
}
