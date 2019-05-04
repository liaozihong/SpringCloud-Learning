package com.dashuai.learning.nacos.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Nacos service provider application
 * <p/>
 * Created in 2019.05.04
 * <p/>
 *
 * @author Liaozihong
 */
@SpringBootApplication
@EnableDiscoveryClient
public class NacosServiceConsumerApplication {
    /**
     * Rest template rest template.
     *
     * @return the rest template
     */
    @LoadBalanced
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(NacosServiceConsumerApplication.class, args);
    }

}
