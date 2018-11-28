package com.dashuai.cloud.client;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {

    @Autowired
    private DiscoveryClient discoveryClient;
    //检查serviceId对应的调用地址
    @Test
    public void serviceUrl() {
        List<ServiceInstance> list = discoveryClient.getInstances("consul-hi");
        if (list != null && list.size() > 0 ) {
            list.forEach(instance-> System.out.println(instance.getUri()+"--------"+instance.getPort()));
        }
    }
}
