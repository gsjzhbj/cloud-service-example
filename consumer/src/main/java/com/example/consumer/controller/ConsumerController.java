package com.example.consumer.controller;

import com.example.consumer.service.IFeignConsumerService;
import com.example.consumer.service.IRestConsumerService;
import com.example.consumer.service.IRibbonConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/consumer")
@RestController
public class ConsumerController {
    @Autowired
    private IRestConsumerService consumerService;

    @Autowired
    private IFeignConsumerService feignConsumerService;

    @Autowired
    private IRibbonConsumerService ribbonConsumerService;

    @GetMapping("/hello")
    public String consumer(@RequestParam("name") String name){
        return consumerService.getHelloInf(name);
    }

    @GetMapping("/feign")
    public String feignConsumer(@RequestParam("name") String name){
        return feignConsumerService.hiFromProvider(name);
    }

    @GetMapping("/ribbon")
    public String ribbonConsumer(@RequestParam("name") String name){
        return ribbonConsumerService.hiFromProvider(name);
    }

    public String defalultFallback(String msg){
        return "error:"+msg;
    }
}
