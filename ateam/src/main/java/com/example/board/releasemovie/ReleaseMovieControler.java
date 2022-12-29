package com.example.board.releasemovie;

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

import com.example.board.review.Review;

import lombok.RequiredArgsConstructor;


	@RequestMapping("/releasemovie")
	@RequiredArgsConstructor
	@Controller
	public class ReleaseMovieControler {

		private final ReleaseMovieService releaseMovieService;

		@GetMapping("/list")
		public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
	        Page<ReleaseMovie> paging = this.releaseMovieService.getList(page);
	        model.addAttribute("paging", paging);
	        return "releasemovie/releasemovie_list";
	    }
	    
	    @GetMapping(value = "/detail/{id}")
	    public String detail(Model model, @PathVariable("id") Integer id) {
	    	ReleaseMovie releaseMovie = this.releaseMovieService.getReleasemMovie(id);
	        model.addAttribute("releaseMovie", releaseMovie);
	        return "releasemovie/releasemovie_detail";
	    }
	    
	    @GetMapping("/create")
	    public String releaseMovieCreate(ReleaseMovieForm releaseMovieForm) {
	    	
	    	//model.addAttribute("releaseMovieForm", releaseMovieForm);
	        return "releasemovie/releasemovie_form";
	    }
	    
	    @PostMapping("/create")
	    public String releaseMovieCreate(@Valid ReleaseMovieForm releaseMovieForm, BindingResult bindingResult) {
	    	System.out.println("test");
	    	try {
	    		if (bindingResult.hasErrors()) {
		            return "releasemovie_form";
		        }
		        this.releaseMovieService.create(releaseMovieForm.getSubject(), releaseMovieForm.getContent(), releaseMovieForm.getName(), releaseMovieForm.getDate());	
			} catch (Exception e) {
				e.printStackTrace();
			}
	        System.out.println("test");
	        return "redirect:/releasemovie/list";
	    }
	}


