package com.dashuai.learning.nacos.provider.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 标记对应服务提供者名
 *
 * @author Liaozihong
 */
@FeignClient(value = "service-provider")
public interface HiService {
    /**
     * Echo string.
     *
     * @param str the name
     * @return the string
     */
    @RequestMapping(value = "/echo/{str}", method = RequestMethod.GET)
    String echo(@PathVariable String str);
}
