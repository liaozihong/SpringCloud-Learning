package com.dashuai.learning.gatewayservice.config;

import com.dashuai.learning.gatewayservice.filter.RequestTimeFilter;
import com.dashuai.learning.gatewayservice.filter.RequestTimeGatewayFilterFactory;
import com.dashuai.learning.gatewayservice.resolver.HostAddrKeyResolver;
import com.dashuai.learning.gatewayservice.resolver.UriKeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * Created in 2019.08.21
 *
 * @author Liaozihong
 */
@Configuration
public class SpringConfiguration {

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
                .route(r -> r.path("/customer/**")
                        .filters(f -> f.filter(new RequestTimeFilter())
                                .addResponseHeader("X-Response-Default-Foo", "Default-Bar"))
                        .uri("http://httpbin.org:80/get")
                        .order(0)
                        .id("customer_filter_router")
                )
                .build();
    }

    /**
     * 将AbstractGatewayFilterFactory 得实现者交由Spring 管理
     *
     * @return request time gateway filter factory
     */
    @Bean
    public RequestTimeGatewayFilterFactory requestTimeGatewayFilterFactory(){
        return new RequestTimeGatewayFilterFactory();
    }

    /**
     * 设置全局过滤器，必须携带token才能访问
     * @return
     */
//    @Bean
//    public GlobalTokenFilter globalTokenFilter(){
//        return new GlobalTokenFilter();
//    }

    /**
     * 使用用户的主机地址进行限流
     *
     * @return host addr key resolver
     */
    @Bean
    public HostAddrKeyResolver hostAddrKeyResolver() {
        return new HostAddrKeyResolver();
    }

    /**
     * 使用Uri去限流
     *
     * @return uri key resolver
     */
    @Bean
    public UriKeyResolver uriKeyResolver() {
        return new UriKeyResolver();
    }

    /**
     * 根据参数值进行限流
     *
     * @return key resolver
     */
    @Bean
    public KeyResolver userKeyResolver() {
        return exchange -> Mono.just(Objects.requireNonNull(exchange.getRequest().getQueryParams().getFirst("user")));
    }
}
