package com.test.test1.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	//메인페이지 접근 - 02.06 장재호
	@RequestMapping("/")
	public String Main() {
		return "common/main";
	}
	
}
