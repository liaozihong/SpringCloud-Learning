package com.amiba.configclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Config client application
 * <p/>
 * Created in 2018.07.17
 * <p/>
 * Spring Cloud Bus 将分布式的节点用轻量的消息代理连接起来。它可以用于广播配置文件的更改或者服务之间的通讯，也可以用于监控。
 * @author <a href="https://github.com/liaozihong" style="background: #55a7e3;">Liaozihong</a>
 */
@SpringBootApplication
@RestController
@EnableEurekaClient
@EnableDiscoveryClient
@RefreshScope
public class ConfigClientApplication {

    /**
     * http://localhost:8882/actuator/bus-refresh
     * 利用Rabbit队列，使用post请求上面链接以达到刷新配置，避免重启服务。
     * /actuator/bus-refresh接口可以指定服务，即使用”destination”参数，
     * 比如 “/actuator/bus-refresh?destination=customers:**” 即刷新服务名为customers的所有服务。
     */

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigClientApplication.class, args);
    }

    /**
     * The Foo.
     */
    @Value("${foo}")
    String path;

    /**
     * Hi string.
     *
     * @return the string
     */
    @RequestMapping("/hi")
    public String hi(){
        return path;
    }
}
