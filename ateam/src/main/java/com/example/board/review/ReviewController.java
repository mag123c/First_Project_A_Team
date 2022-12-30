package com.example.board.review;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.board.review.comment.CommentForm;

import lombok.RequiredArgsConstructor;

@RequestMapping("/review")
@RequiredArgsConstructor
@Controller
public class ReviewController {

	private final ReviewService reviewService;

	@PreAuthorize("isAuthenticated()")
	@GetMapping("/list")
    public String list(/* Model model, @RequestParam(value="page", defaultValue="0") int page */) {
//        Page<Review> paging = this.reviewService.getList(page);
//        model.addAttribute("paging", paging);
        return "/review/review_list";
    }
	
    
//    @GetMapping(value = "/detail/{id}")
//    public String detail(Model model, @PathVariable("id") Integer id, CommentForm commentForm) {
//    	Review review = this.reviewService.getReview(id);
//        model.addAttribute("review", review);
//        return "/review/review_detail";
//    }
//    
//    @GetMapping("/create")
//    public String reviewCreate(ReviewForm reviewForm) {
//        return "/review/review_form";
//    }
//    
//    @PostMapping("/create")
//    public String reviewCreate(@Valid ReviewForm reviewForm, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "/review/review_form";
//        }
//        this.reviewService.create(reviewForm.getSubject(), reviewForm.getContent());
//        return "redirect:/review/list";
//    }
}