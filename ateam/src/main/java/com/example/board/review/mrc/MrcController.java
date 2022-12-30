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
	public String create(Model model) {
		List<Mrc1> mrc1List = this.mrcService.getList();
		model.addAttribute("mrc1List", mrc1List);
		
		return "/review/movie1";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/movie1")
	public String create(@Valid MrcForm mrcForm, 
			BindingResult bindingResult, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "redirect:/review/movie1";
		}
		SiteUser siteuser = this.userService.getUser(principal.getName());
		this.mrcService.create(mrcForm.getContent(),siteuser);
		
		System.out.println(siteuser.getUsername());
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
    @GetMapping("/delete/{id}")
    public String mrcDelete(Principal principal, @PathVariable("id") Integer id) {
        Mrc1 mrc1 = this.mrcService.getMrc1(id);
        if (!mrc1.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
        }
        this.mrcService.delete(mrc1);
        return "/review/movie1";
    }
	

	
    
//    @PreAuthorize("isAuthenticated()")
//    @PostMapping("/movie1")
//    public String commentModify(@Valid MrcForm mrcForm, BindingResult bindingResult, 
//            Principal principal, @PathVariable("id") Integer id) {
//        if (bindingResult.hasErrors()) {
//            return "/review/movie1";
//        }
//        Mrc1 mrc1 = this.mrcService.getMrc1(id);
//        if (!mrc1.getAuthor().getUsername().equals(principal.getName())) {
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다.");
//        }
//        this.mrcService.modify(mrc1, mrcForm.getContent());
//        return "redirect:/review/movie1";
//    }
	
	
	
	
	
	
	
//	@PreAuthorize("isAuthenticated()")
//	@GetMapping("/movie2")
//	public String create2(Model model) {
//		List<Mrc2> mrc2List = this.mrcService.getList2();
//		model.addAttribute("mrc2List", mrc2List);
//		
//		return "/review/movie2";
//	}
//	
//	@PreAuthorize("isAuthenticated()")
//	@PostMapping("/movie2")
//	public String create2(@Valid MrcForm mrcForm, 
//			BindingResult bindingResult, Principal principal) {
//		if(bindingResult.hasErrors()) {
//			return "/review/movie2";
//		}
//		SiteUser siteuser = this.userService.getUser(principal.getName());
//		this.mrcService.create(mrcForm.getContent(),siteuser);
//		
//		return "redirect:/review/movie2";
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	@PreAuthorize("isAuthenticated()")
//	@GetMapping("/movie3")
//	public String create3(Model model) {
//		List<Mrc3> mrc3List = this.mrcService.getList3();
//		model.addAttribute("mrc3List", mrc3List);
//		
//		return "/review/movie3";
//	}
//	
//	@PreAuthorize("isAuthenticated()")
//	@PostMapping("/movie3")
//	public String create3(@Valid MrcForm mrcForm, 
//			BindingResult bindingResult, Principal principal) {
//		if(bindingResult.hasErrors()) {
//			return "/review/movie3";
//		}
//		SiteUser siteuser = this.userService.getUser(principal.getName());
//		this.mrcService.create(mrcForm.getContent(),siteuser);
//		
//		return "redirect:/review/movie3";
//	}
    
    
	
}
