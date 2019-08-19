package com.dashuai.cloud.consulconsumerfeign.config;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Hystrix servlet definitions
 * <p/>
 * Created in 2018.11.28
 * <p/>
 *
 * @author Liaozihong
 */
@Configuration
public class HystrixServletDefinitions {
    /**
     * Servlet registration bean servlet registration bean.
     * 配置注入HystrixRegistrationBean，否则访问/hystrix.stream 失败!
     * @return the servlet registration bean
     */
    @Bean(name = "hystrixRegistrationBean")
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(
                new HystrixMetricsStreamServlet(), "/hystrix.stream");
        registration.setName("hystrixServlet");
        registration.setLoadOnStartup(1);
        return registration;
    }
}
