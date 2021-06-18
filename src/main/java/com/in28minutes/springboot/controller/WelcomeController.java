package com.in28minutes.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.springboot.configuration.BasicConfiguration;
import com.in28minutes.springboot.service.WelcomeService;

@RestController

public class WelcomeController{
	
	@Autowired
	private WelcomeService service;
	
	@Autowired
	private BasicConfiguration configuration;
	
	@RequestMapping(value="/welcome")
	public String WelcomeMessage() {
		return service.retrieveMessage();
	}
	
	@RequestMapping(value="/dynamicConfiguartion")
	public Map DynamicMessage() {
		System.out.println("dynamic config");
		Map map = new HashMap();
		map.put("message",configuration.getMessage());
		map.put("value",configuration.isValue());
		map.put("number",configuration.getNumber());
		return map;
		
	}
	
	
	
}

