package com.example.consumer.service;

import com.example.consumer.fallback.FeignConsumerServiceFallback;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "spring-eureka-provider",fallback = FeignConsumerServiceFallback.class)
public interface IFeignConsumerService {


    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    String hiFromProvider(@RequestParam("name") String name);


}
