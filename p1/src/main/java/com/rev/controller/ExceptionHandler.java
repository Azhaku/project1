package com.rev.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public String allexceptions(Exception e) {
		System.out.println(e.getMessage());
		return "redirect:/login";
	}
}
