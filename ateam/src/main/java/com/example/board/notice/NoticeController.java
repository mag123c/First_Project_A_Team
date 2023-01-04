package com.example.board.notice;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.example.board.user.SiteUser;
import com.example.board.user.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/notice")
@RequiredArgsConstructor  
@Controller
public class NoticeController {

	private final NoticeService noticeService;
	private final UserService userService;
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/list")
    public String list(Model model, @RequestParam(value="page", defaultValue="0") int page, @RequestParam(value="kw", defaultValue="") String kw) {
        Page<Notice> paging = this.noticeService.getList(page,kw);
        model.addAttribute("paging", paging);
        model.addAttribute("kw", kw);
        return "/notice/notice_list";
    }
	
	@PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
    	Notice notice = this.noticeService.getnotice(id);
        model.addAttribute("notice", notice);
        return "/notice/notice_detail";
    }
	
	@PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public String noticeCreate(NoticeForm noticeForm) {
        return "/notice/notice_form";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String noticeCreate(@Valid NoticeForm noticeForm, BindingResult bindingResult, Principal principal) {
        if (bindingResult.hasErrors()) {
            return "/notice/notice_form";
        }
        SiteUser siteuser = this.userService.getUser(principal.getName());
		this.noticeService.create(
				noticeForm.getSubject(), 
				noticeForm.getContent(),
				siteuser);
		return "redirect:/notice/list";
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/modify/{id}")
    public String noticeModify(NoticeForm noticeForm, @PathVariable("id") Integer id, Principal principal) {
        Notice notice = this.noticeService.getnotice(id);
        if(!notice.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "�닔�젙沅뚰븳�씠 �뾾�뒿�땲�떎.");
        }
        noticeForm.setSubject(notice.getSubject());
        noticeForm.setContent(notice.getContent());
        return "/notice/notice_modify";
    }
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")
    public String noticeModify(@Valid NoticeForm noticeForm, BindingResult bindingResult, 
            Principal principal, @PathVariable("id") Integer id) {
        if (bindingResult.hasErrors()) {
            return "/notice/notice_form";
        }
        Notice notice = this.noticeService.getnotice(id);
        if (!notice.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "�닔�젙沅뚰븳�씠 �뾾�뒿�땲�떎.");
        }
        this.noticeService.modify(notice, noticeForm.getSubject(), noticeForm.getContent());
        return String.format("redirect:/notice/detail/%s", id);
    }
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/delete/{id}")
    public String noticeDelete(Principal principal, @PathVariable("id") Integer id) {
        Notice notice = this.noticeService.getnotice(id);
        if (!notice.getAuthor().getUsername().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "�궘�젣沅뚰븳�씠 �뾾�뒿�땲�떎.");
        }
        this.noticeService.delete(notice);
        return "redirect:/notice/list";
    }
    
}