package com.example.board.review.mrc;

import java.security.Principal;
import java.util.List;
import java.util.logging.Logger;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.board.review.ReviewForm;
import com.example.board.user.SiteUser;
import com.example.board.user.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/review/movie1")
@RequiredArgsConstructor
@Controller
public class MrcController {

	private final MrcService mrcService;
	private final UserService userService;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("")
	public String create(Model model) {
		List<Mrc1> mrc1List = this.mrcService.getList();
		model.addAttribute("mrc1List", mrc1List);
		
		return "/review/movie1";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("")
	public String create(@Valid MrcForm mrcForm, 
			BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "redirect:/review/movie1";
		}
		SiteUser siteuser = this.userService.getUser(principal.getName());
		this.mrcService.create(mrcForm.getContent(),siteuser);
		
		return "redirect:/review/movie1";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String mrcModify(MrcForm mrcForm, @PathVariable("id") Integer id, Principal principal) {
	    Mrc1 mrc1 = this.mrcService.getMrc1(id);
	    if (!mrc1.getAuthor().getUsername().equals(principal.getName())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	    }
	    mrcForm.setContent(mrc1.getContent());
	    return "/review/review_form";
	}
	
	
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String mrcModify(@Valid MrcForm mrcForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "redirect:/review/movie1";
        }
        Mrc1 mrc1 = this.mrcService.getMrc1(id);
        if (!mrc1.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.mrcService.modify(mrc1, mrcForm.getContent());
        return "redirect:/review/movie1";
    }
	
	
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String mrcDelete(Principal principal, @PathVariable("id") Integer id) {
        Mrc1 mrc1 = this.mrcService.getMrc1(id);
        if (!mrc1.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.mrcService.delete(mrc1);
        return "redirect:/review/movie1";
    }	
       
	
}
