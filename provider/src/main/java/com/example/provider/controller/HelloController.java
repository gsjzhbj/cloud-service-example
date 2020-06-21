package com.example.provider.controller;

import com.example.provider.service.IHelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

	@Autowired
	private IHelloService helloService;

	@RequestMapping("/hello")
	public Object index(@RequestParam String name) {

		return helloService.hello(name);
	}

}
