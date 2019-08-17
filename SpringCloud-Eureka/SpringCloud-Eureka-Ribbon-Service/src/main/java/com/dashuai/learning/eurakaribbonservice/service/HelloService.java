package com.dashuai.learning.eurakaribbonservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Hello service
 * <p/>
 * Created in 2018.07.16
 * <p/>
 *
 * @author <a href="https://github.com/liaozihong" style="background: #55a7e3;">Liaozihong</a>
 */
@Service
public class HelloService {
    @Autowired
    RestTemplate restTemplate;

    /**
     * Hi service string.
     * 通过restTemplate去发送。
     * @param name the name
     * @return the string
     */
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name){
        return restTemplate.getForObject("http://SERVICE-HI/hi?name="+name,String.class);
    }
    public String hiError(String name){
        return "hi,"+ name + ",sorry,error！";
    }
}
