package com.dashuai.learning.gatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
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
     * 在上面的myRoutes方法中，使用了一个RouteLocatorBuilder的bean去创建路由，除了创建路由RouteLocatorBuilder可以
     * 让你添加各种predicates和filters，predicates断言的意思，顾名思义就是根据具体的请求的规则，由具体的route去
     * 处理，filters是各种过滤器，用来对请求做各种判断和修改。
     * <p>
     * 上面创建的route可以让请求“/get”请求都转发到“http://httpbin.org/get”。在route配置上，我们添加了一个
     * filter，该filter会将请求添加一个header,key为hello，value为world。
     * 在spring cloud gateway中可以使用Hystrix。Hystrix是 spring cloud中一个服务熔断降级的组件，在微服务系统有着十分重要的作用。
     * Hystrix是 spring cloud gateway中是以filter的形式使用的
     *
     * @param builder the builder
     * @return route locator
     */
    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        String httpUri = "http://httpbin.org:80";
        return builder.routes()
                .route(p -> p
                        .path("/get")
                        .filters(f -> f.addRequestHeader("Hello", "World"))
                        .uri(httpUri))
                .route(p -> p
                        .host("*.hystrix.com")
                        .filters(f -> f
                                .hystrix(config -> config
                                        .setName("mycmd")
                                        .setFallbackUri("forward:/fallback")))
                        .uri(httpUri))
                .build();
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
