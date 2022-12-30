package com.example.board.notice;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.RequiredArgsConstructor;

@RequestMapping("/notice")
@RequiredArgsConstructor
@Controller
public class NoticeController {

	private final NoticeService noticeService;

	@GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Notice> paging = this.noticeService.getList(page);
        model.addAttribute("paging", paging);
        return "/notice/notice_list";
    }
    
    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
    	Notice notice = this.noticeService.getNotice(id);
        model.addAttribute("notice", notice);
        return "/notice/notice_detail";
    }
    
    @GetMapping("/create")
    public String noticeCreate(NoticeForm noticeForm) {
        return "/notice/notice_form";
    }
    
    @PostMapping("/create")
    public String noticeCreate(@Valid NoticeForm noticeForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/notice/notice_form";
        }
        this.noticeService.create(noticeForm.getSubject(), noticeForm.getContent());
        return "redirect:/notice/list";
    }
}