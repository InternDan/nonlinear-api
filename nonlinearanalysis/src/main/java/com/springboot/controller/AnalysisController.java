package com.springboot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class AnalysisController {

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}
}