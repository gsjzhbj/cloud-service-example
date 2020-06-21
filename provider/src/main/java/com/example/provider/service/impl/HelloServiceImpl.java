package com.example.provider.service.impl;

import com.example.provider.service.IHelloService;
import org.springframework.stereotype.Service;

@Service
public class HelloServiceImpl implements IHelloService {
    @Override
    public String hello(String name) {
        return "Hello "+name;
    }
}
