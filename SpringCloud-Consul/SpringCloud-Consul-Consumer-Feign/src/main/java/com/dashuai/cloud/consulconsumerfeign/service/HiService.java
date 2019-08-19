package com.dashuai.cloud.consulconsumerfeign.service;

import com.dashuai.cloud.consulconsumerfeign.service.hystric.HiServiceHystric;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Hi service.
 *
 * @author Liaozihong
 */
@FeignClient(value = "consul-hi",fallback = HiServiceHystric.class)
@Component
public interface HiService {
    /**
     * Say hi from client one string.
     *
     * @param name the name
     * @return the string
     */
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
