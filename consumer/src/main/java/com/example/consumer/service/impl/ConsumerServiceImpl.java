package com.example.consumer.service.impl;

import com.example.consumer.service.IRestConsumerService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConsumerServiceImpl implements IRestConsumerService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Value("${eurekaURL}")
    private String eurekaURL;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "defaultFallback")
    @Override
    public String getHelloInf(String name){
//        return restTemplate.getForObject(eurekaURL+"/hello?name="+name,String.class);
        //第二种方式
        ServiceInstance serviceInstance = loadBalancerClient.choose("spring-eureka-provider");
        String url = String.format("http://%s:%s",serviceInstance.getHost(),serviceInstance.getPort()) + "/hello?name="+name;
        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url,String.class,name);
        logger.info("response:" + response);
        return response;
    }
    public String defaultFallback(String msg){
        return "rest fallback "+msg;
    }
}
