package com.example.consumer.service;

import org.springframework.web.bind.annotation.RequestParam;

public interface IRibbonConsumerService {
    String hiFromProvider(@RequestParam("name") String name);
}
