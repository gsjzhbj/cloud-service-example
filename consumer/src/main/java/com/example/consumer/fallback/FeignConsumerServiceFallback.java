package com.example.consumer.fallback;

import com.example.consumer.service.IFeignConsumerService;
import org.springframework.stereotype.Component;

@Component
public class FeignConsumerServiceFallback implements IFeignConsumerService {
    @Override
    public String hiFromProvider(String name) {
        return "error "+name;
    }

}
