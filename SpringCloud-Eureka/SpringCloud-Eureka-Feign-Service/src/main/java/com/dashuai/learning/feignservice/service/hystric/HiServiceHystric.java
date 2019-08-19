package com.dashuai.learning.feignservice.service.hystric;

import com.dashuai.learning.feignservice.service.HiService;
import org.springframework.stereotype.Component;

@Component
public class HiServiceHystric implements HiService {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry,invoker error"+name;
    }
}
