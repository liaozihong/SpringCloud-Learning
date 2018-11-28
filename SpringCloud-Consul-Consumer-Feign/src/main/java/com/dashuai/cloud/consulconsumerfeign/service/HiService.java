package com.dashuai.cloud.consulconsumerfeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The interface Hi service.
 *
 * @author Liaozihong
 */
@FeignClient(value = "consul-hi")
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
