package com.hh.consul.keyvalue;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Rest controller
 * <p/>
 * Created in 2018.08.29
 * <p/>
 *
 * @author Liaozihong
 */
@RestController
@RefreshScope
public class DemoController {
    @Value("${author}")
    private String author;

    @Value("${age}")
    private String age;

    /**
     * Demo string.
     *
     * @return the string
     */
    @GetMapping("/demo")
    public String demo() {
        return author + "," + age;
    }
    @GetMapping("/actuator/info")
    public String health(){
        return new Date().toString();
    }
}
