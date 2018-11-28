package com.dashuai.learning.feignservice;

import com.dashuai.learning.feignservice.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service invoke api
 * <p/>
 * Created in 2018.11.28
 * <p/>
 *
 * @author Liaozihong
 */
@RestController
public class ServiceInvokeApi {
    /**
     * 编译器报错，无视。 因为这个Bean是在程序启动的时候注入的，编译器感知不到，所以报错。
     */
    @Autowired
    HiService hiService;

    /**
     * Say hi string.
     *
     * @param name the name
     * @return the string
     */
    @GetMapping(value = "/hi")
    public String sayHi(@RequestParam String name) {
        return hiService.sayHiFromClientOne( name );
    }
}
