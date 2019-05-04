package com.dashuai.learning.nacos.provider.api;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    /**
     * Echo string.
     *
     * @param string the string
     * @return the string
     */
    @RequestMapping(value = "/echo/{string}", method = RequestMethod.GET)
    public String echo(@PathVariable String string) {
        return "Hello Nacos Discovery " + string;
    }
}
