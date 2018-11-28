package com.dashuai.learning.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * Gateway service application
 * <p/>
 * Created in 2018.11.28
 * <p/>
 * 配置服务经过Zuul在记录到Zipkin 上，可追踪调用记录链
 * @author Liaozihong
 */
@EnableZuulProxy
@SpringBootApplication
public class GatewayServiceApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}
