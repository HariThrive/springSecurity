package com.springSecurity.springSecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/product")
public class Product {
	
	@GetMapping("/")
	@ResponseBody
	public String print() {
		return "hello world";
	}

}
