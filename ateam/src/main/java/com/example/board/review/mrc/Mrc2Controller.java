package com.example.board.review.mrc;

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

@RequestMapping("/review/movie2")
@RequiredArgsConstructor
@Controller
public class Mrc2Controller {
	
	private final Mrc2Service mrc2Service;
	private final UserService userService;
	
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("")
	public String create2(Model model) {
		List<Mrc2> mrc2List = this.mrc2Service.getList();
		model.addAttribute("mrc2List", mrc2List);
		
		return "/review/movie2";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("")
	public String create2(@Valid MrcForm mrcForm, 
			BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "redirect:/review/movie2";
		}
		SiteUser siteuser = this.userService.getUser(principal.getName());
		this.mrc2Service.create(mrcForm.getContent(),siteuser);
		
		return "redirect:/review/movie2";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String mrc2Modify(MrcForm mrcForm, @PathVariable("id") Integer id, Principal principal) {
	    Mrc2 mrc2 = this.mrc2Service.getMrc(id);
	    if (!mrc2.getAuthor().getUsername().equals(principal.getName())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
	    }
	    mrcForm.setContent(mrc2.getContent());
	    return "/review/review_form";
	}
	
	
	@PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String mrc2Modify(@Valid MrcForm mrcForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "redirect:/review/movie2";
        }
        Mrc2 mrc2 = this.mrc2Service.getMrc(id);
        if (!mrc2.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
        }
        this.mrc2Service.modify(mrc2, mrcForm.getContent());
        return "redirect:/review/movie2";
    }
	
	
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String mrc2Delete(Principal principal, @PathVariable("id") Integer id) {
        Mrc2 mrc2 = this.mrc2Service.getMrc(id);
//        if (!mrc2.getAuthor().getUsername().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
//        }
        this.mrc2Service.delete(mrc2);
        return "redirect:/review/movie2";
    }
	
}
