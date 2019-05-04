package com.dashuai.learning.nacos.provider.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
     * Echo string.
     *
     * @param str the string
     * @return the string
     */
    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    public String echo(@PathVariable String str) {
        return restTemplate.getForObject("http://service-provider/echo/" + str, String.class);
    }
}
