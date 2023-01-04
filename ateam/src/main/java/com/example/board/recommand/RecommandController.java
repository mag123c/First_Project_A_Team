package com.example.board.recommand;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import com.example.board.user.SiteUser;
import com.example.board.user.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/recommandMain")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
@Controller
public class RecommandController {

	private final RecommandService recommandService;
	private final RecommandRepository recommandRepository;
	private final UserService userService;
	
	
    @PreAuthorize("isAuthenticated()")
	@GetMapping("")
	public String recommandRoot() {
		return "recommand/recommandMain";
	}
	
    @PreAuthorize("isAuthenticated()")
	@GetMapping("/list")
	public String recommandList(Model model, RecommandForm recommandForm) {
		// 정보 view단으로 보내기.  
		List<Recommand> recommandList = this.recommandRepository.findAll();
		model.addAttribute("recommandList",recommandList);		
	
		return "recommand/recommand_list";
	}
	

	// 질문등록 -------------------------------------------------------------------
	
    @PreAuthorize("isAuthenticated()")
	@GetMapping("/list/create")
	public String create(RecommandForm recommandForm) {
		return "recommand/recommand_create";
	}
	
    @PreAuthorize("isAuthenticated()")
	@PostMapping("/list/create")
	public String create(@Valid RecommandForm recommandForm, 
			BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "recommand/recommand_form";
		}


		SiteUser siteuser = this.userService.getUser(principal.getName());
		this.recommandService.create(
				recommandForm.getContent(),
				siteuser);
		return "redirect:/recommandMain/list";   // /뒤에 redirect를 할 경우, localhost:8080뒤에 해당경로가 바로 붙고, /없이 recommandMain/list할 경우, 현재 들어가있는 주소에 합쳐진다.
	}
	
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String recommandModify(RecommandForm recommandForm,  @PathVariable("id") Integer id, Principal principal) {

    	Recommand recommand = this.recommandService.getRecommand(id);
//        if(!recommand.getAuthor().getUsername().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//        }
        
        recommandForm.setContent(recommand.getContent());
       
        
        return "/recommand/recommand_modify";  
        
    }
    
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String recommandModify(@Valid RecommandForm recommandForm, BindingResult bindingResult,  Principal principal, @PathVariable("id") Integer id) {

    	 if (bindingResult.hasErrors()) {
             return "/recommand/recommand_modify";
         }
    	
    	Recommand recommand = this.recommandService.getRecommand(id);
//        if(!recommand.getAuthor().getUsername().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//        }
        this.recommandService.modify(recommand, recommandForm.getContent());
        
        return "redirect:/recommandMain/list";  
        
    }
   
    
    // 삭제 관련 
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String recommandDelete(Principal principal, @PathVariable("id") Integer id) {
    	Recommand recommand = this.recommandService.getRecommand(id);
//        if (!recommand.getAuthor().getUsername().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
//        }
        this.recommandService.delete(recommand);
        return "redirect:/recommandMain/list";
    }
    
    
    
    
    
    
    
    
}
