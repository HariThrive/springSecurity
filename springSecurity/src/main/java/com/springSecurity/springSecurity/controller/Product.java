package com.springSecurity.springSecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
//@RequestMapping("/auth")
public class Product {
	
	@GetMapping("/")
	@ResponseBody
	public String print() {
		return "hello world";
	}
	
	@GetMapping("/register")
    public String registerPage() {
        return "html/register"; // templates/register.html
    }
	

	@GetMapping("/login")
    public String loginPage() {
        return "html/login"; // templates/register.html
    }
	
	 @GetMapping("/index")
	    public String indexPage(Authentication authentication) {
	        return "html/index";   
	    }

}
