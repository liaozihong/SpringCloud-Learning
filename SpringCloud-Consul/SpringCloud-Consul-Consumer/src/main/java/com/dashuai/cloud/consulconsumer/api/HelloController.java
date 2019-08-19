package com.dashuai.cloud.consulconsumer.api;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Hello controller
 * <p/>
 * Created in 2018.08.05
 * <p/>
 *
 * @author Liaozihong
 */
@RestController
public class HelloController {
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    RestTemplate restTemplate;

    /**
     * 获取所有服务
     *
     * @return the object
     */
    @RequestMapping("/services")

    public Object services() {
        return discoveryClient.getInstances("consul-hi");
    }

    /**
     * 从所有服务中选择一个服务（轮询）
     *
     * @return the object
     */
    @RequestMapping("/discover")
    public Object discover() {
        return loadBalancerClient.choose("consul-hi").getUri().toString();
    }

    /**
     * Say hi string.
     *
     * @param name the name
     * @return the string
     */
    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "hiError")
    public String sayHi(String name) {
        ServiceInstance serviceInstance = loadBalancerClient.choose("consul-hi");
        System.out.println("服务地址：" + serviceInstance.getUri());
        System.out.println("服务名称：" + serviceInstance.getServiceId());
        String callServiceResult = restTemplate.getForObject(serviceInstance.getUri().toString() + "/hi?name=" + name, String.class);
        System.out.println(callServiceResult);
        return callServiceResult;
    }


    public String hiError(String name) {
        return "hi," + name + ",sorry,error！";
    }
}
