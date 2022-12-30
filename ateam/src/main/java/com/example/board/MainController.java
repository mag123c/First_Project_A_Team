package com.example.board;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	
	@GetMapping("/")
	public String main() {
		return "/pages/index";
	}
	
    @GetMapping("/signInUp")
    public String signInUp() {
        return "/pages/signInUp";
    }
    
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/main")
	public String lay() {
		return "/pages/lay";
	}
    
	@GetMapping("/help")
	public String help() {
		return "/pages/helper";
	}
	
}
