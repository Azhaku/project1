package com.rev.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	@GetMapping("/")
	public String getMethodName() {
		return "index";
	}
	
//	@GetMapping("/error")
//	public String errorPage() {
//		return "/logout";
//	}
	

}
