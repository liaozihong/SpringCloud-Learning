package com.dashuai.cloud.consulconsumerfeign.service.hystric;

import com.dashuai.cloud.consulconsumerfeign.service.HiService;
import org.springframework.stereotype.Component;

@Component
public class HiServiceHystric implements HiService {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry,invoker error"+name;
    }
}
