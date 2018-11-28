package com.hh.consul.keyvalue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Key value application
 * <p/>
 * Created in 2018.08.29
 * <p/>
 * 启用定时调度功能，Consul需要使用此功能来监控配置改变
 * @author Liaozihong
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
public class KeyValueApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(KeyValueApplication.class, args);
    }
}
