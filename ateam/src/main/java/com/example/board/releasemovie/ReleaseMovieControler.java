package com.example.board.releasemovie;

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


	@RequestMapping("/releasemovie")
	@RequiredArgsConstructor
	@Controller
	public class ReleaseMovieControler {

		private final ReleaseMovieService releaseMovieService;
		private final UserService userService;
		
		//list HTML 구성
		@GetMapping("/list")
		public String list(Model model, @RequestParam(value="page", defaultValue="0") int page) {
	        Page<ReleaseMovie> paging = this.releaseMovieService.getList(page);
	        model.addAttribute("paging", paging);
	        return "releasemovie/releasemovie_list";
	    }
	    
		//페이징
	    @GetMapping(value = "/detail/{id}")
	    public String detail(Model model, @PathVariable("id") Integer id) {
	    	ReleaseMovie releaseMovie = this.releaseMovieService.getReleasemMovie(id);
	        model.addAttribute("releaseMovie", releaseMovie);
	        return "releasemovie/releasemovie_detail";
	    }
	    
	    @PreAuthorize("isAuthenticated()")
	    @GetMapping("/create")
	    public String releaseMovieCreate(ReleaseMovieForm releaseMovieForm) {
	    	
	    	//model.addAttribute("releaseMovieForm", releaseMovieForm);
	        return "releasemovie/releasemovie_form";
	    }
	    
	    //댓글 생성
	    @PreAuthorize("isAuthenticated()")
	    @PostMapping("/create")
	    public String releaseMovieCreate(@Valid ReleaseMovieForm releaseMovieForm, BindingResult bindingResult, Principal principal) {
	    	System.out.println("test");
	    	try {
	    		if (bindingResult.hasErrors()) {
		            return "releasemovie_form";
		        }
	    		SiteUser siteuser = this.userService.getUser(principal.getName());
		        this.releaseMovieService.create(releaseMovieForm.getSubject(),siteuser);	
			} catch (Exception e) {
				e.printStackTrace();
			}
	        System.out.println("test");
	        return "redirect:/releasemovie/list";
	    }
	    
	    //댓글 수정
	    @GetMapping("/modify/{id}")
		public String modify(ReleaseMovieForm releaseMovieForm, 
				@PathVariable("id") Integer id, Principal principal) {
			//서비스로 넘겨서 수정내용을 저장.
	    	ReleaseMovie releaseMovie = this.releaseMovieService.getReleasemMovie(id);
			// 수정권한을 2차로 체크.
			// 글쓴이와 로그인한 사용자가 다르다면
			if(!releaseMovie.getAuthor().getUsername().equals(principal.getName())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다");
			}
			releaseMovieForm.setSubject(releaseMovie.getSubject());
			return "releasemovie_modify";
	    }
	    
	    @PreAuthorize("isAuthenticated()")
		@PostMapping("/modify/{id}")
		public String modify(@Valid ReleaseMovieForm releaseMovieForm, @PathVariable("id") Integer id, 
				BindingResult bindingResult, Principal principal) {
			
			if(bindingResult.hasErrors()) {
				return "releasemovie_form";
			}
			ReleaseMovie releaseMovie = this.releaseMovieService.getReleasemMovie(id);
			if(!releaseMovie.getAuthor().getUsername().equals(principal.getName())){
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정권한이 없습니다");
			}
			this.releaseMovieService.modify(releaseMovie, releaseMovieForm.getSubject());
			
			return String.format("redirect:/releasemovie/list/%s", id); //id값이 어디로 넘어가야 하는가
	    }
	    
		//삭제
		@PreAuthorize("isAuthenticated()")
		@GetMapping("/delete/{id}")
		public String delete(@PathVariable("id") Integer id, Principal principal) {
			ReleaseMovie releaseMovie = this.releaseMovieService.getReleasemMovie(id);
			if(!releaseMovie.getAuthor().getUsername().equals(principal.getName())) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "권한 없음");
			}
			this.releaseMovieService.delete(releaseMovie);
			return "redirect:/releasemovie/list";
				
		}
	}


