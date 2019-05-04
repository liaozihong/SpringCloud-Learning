package com.dashuai.learning.nacos.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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
public class NacosServiceProviderApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(NacosServiceProviderApplication.class, args);
    }

}
