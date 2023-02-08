package com.test.test1.video.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.test.test1.video.service.VideoService;

@Controller
public class VideoController { 
		
		@Autowired 
		VideoService videoService; 
		
		@RequestMapping(value="/create", method = RequestMethod.GET)
		public ModelAndView create() {
		    return new ModelAndView("/video/create");
		}	
		
		@RequestMapping(value = "/create", method = RequestMethod.POST)
		public ModelAndView createPost(@RequestParam Map<String, Object> map) {
		    ModelAndView mav = new ModelAndView();

		    String videoId = this.videoService.create(map);
		    if (videoId == null) {
		        mav.setViewName("redirect:/video/create");
		    }else {
		        mav.setViewName("redirect:/detail?videoId=" + videoId); 
		    }  

		    return mav;
		}

	   
		@RequestMapping(value = "/detail", method = RequestMethod.GET)
		public ModelAndView detail(@RequestParam Map<String, Object> map) {
		    Map<String, Object> detailMap = this.videoService.detail(map);
		    ModelAndView mav = new ModelAndView();
		    mav.addObject("data", detailMap);
		    mav.setViewName("/video/detail");
		    return mav;
		}	   

}
