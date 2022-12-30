package com.example.board.review.mrc;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.board.review.ReviewForm;
import com.example.board.user.SiteUser;
import com.example.board.user.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/review")
@RequiredArgsConstructor
@Controller
public class MrcController {

	private final MrcService mrcService;
	private final UserService userService;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/movie1")
	public String movie1(Model model) {
		List<Mrc1> mrc1List = this.mrcService.getList();
		model.addAttribute("mrc1List", mrc1List);
		
		return "/review/movie1";
	}
	
//    @PostMapping("/movie1")
//    public String reviewCreate(@Valid MrcForm mrcForm, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "redirect:/review/movie1";
//        }
//        this.mrcService.create(mrcForm.getContent());
//        return "redirect:/review/movie1";
//    }	

	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/movie1")
	public String create(@Valid MrcForm mrcForm, 
			BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "/review/movie1";
		}
		SiteUser siteuser = this.userService.getUser(principal.getName());
		this.mrcService.create(mrcForm.getContent(),siteuser);
		
		System.out.println(siteuser.getUsername());
		return "redirect:/review/movie1";
	}
    
    
	
}
