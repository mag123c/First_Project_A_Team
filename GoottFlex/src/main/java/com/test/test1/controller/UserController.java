package com.test.test1.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.test.test1.service.UserService;
import com.test.test1.service.VideoService;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/signup", method = RequestMethod.GET)
	public ModelAndView create() {
	    return new ModelAndView("/user/create");
	}	
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ModelAndView createPost(@RequestParam Map<String, Object> map) {
	    ModelAndView mav = new ModelAndView();

	    String userId = this.userService.create(map);
	    if (userId == null) {
	        mav.setViewName("redirect:/user/create");
	    }else {
	        mav.setViewName("redirect:/detail?userId=" + userId); 
	    }  

	    return mav;
	}
	
}