package com.example.consumer.service.impl;

import com.example.consumer.service.IRibbonConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class RibbonConsumerServiceImpl implements IRibbonConsumerService {

    @Autowired
    RestTemplate restTemplate;
    @HystrixCommand(fallbackMethod = "defaultFallback")
    @Override
    public String hiFromProvider(String name) {
        String response= restTemplate.getForObject("http://spring-eureka-provider/hello?name="+name,String.class,name);
        return response;
    }

    public String defaultFallback(String msg){
        return "fallback";
    }
}
