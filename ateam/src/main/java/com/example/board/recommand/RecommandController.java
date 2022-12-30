package com.example.board.recommand;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequestMapping("/recommandMain")
@RequiredArgsConstructor
@Controller
public class RecommandController {

	private final RecommandService recommandService;
	
	@GetMapping("")
	public String recommandRoot() {
		
		return "recommand/recommandMain";
	}
	
	@GetMapping("/list")
	public String recommandRoot(Model model, RecommandForm recommandForm,  @RequestParam(value="page", defaultValue = "0") int page, 
			 @RequestParam(value="kw", defaultValue = "") String kw) {
		// 정보 view단으로 보내기.  
		Recommand recommand = new Recommand();
		model.addAttribute("recommand",recommand);
		
		// 페이징처리
		Page<Recommand> paging = this.recommandService.getList(page, kw);		
		model.addAttribute("paging",paging);
		model.addAttribute("kw", kw);
		
		return "recommand/recommand_list";
	}
	
	
	
	
}
