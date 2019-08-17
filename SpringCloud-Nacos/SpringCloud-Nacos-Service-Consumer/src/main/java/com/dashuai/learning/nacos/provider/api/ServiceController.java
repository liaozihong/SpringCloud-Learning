package com.dashuai.learning.nacos.provider.api;

import com.dashuai.learning.nacos.provider.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Service controller
 * <p/>
 * Created in 2019.05.04
 * <p/>
 *
 * @author Liaozihong
 */
@RestController
public class ServiceController {
    @Autowired
    private  RestTemplate restTemplate;
    /**
     * The Hi service.
     */
    @Autowired
    HiService hiService;

    /**
     * 基于Ribbon+restTemplate的方式调用
     *
     * @param str the string
     * @return the string
     */
    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        return restTemplate.getForObject("http://service-provider/echo/" + str, String.class);
    }

    /**
     * 基于Feign的方式调用
     *
     * @param str the str
     * @return the string
     */
    @RequestMapping(value = "/echo/v2/{str}", method = RequestMethod.GET)
    public String feignInvoke(@PathVariable String str) {
        return hiService.echo(str);
    }
}
