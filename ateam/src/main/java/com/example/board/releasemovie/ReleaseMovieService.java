package com.example.board.releasemovie;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.board.DataNotFoundException;
import com.example.board.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ReleaseMovieService {

    private final ReleasemMovieRepository releasemMovieRepository;

    public Page<ReleaseMovie> getList(int page) {
    	List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.releasemMovieRepository.findAll(pageable);
    }
    
    public ReleaseMovie getReleasemMovie(Integer id) {  
        Optional<ReleaseMovie> releaseMovie = this.releasemMovieRepository.findById(id);
        if (releaseMovie.isPresent()) {
            return releaseMovie.get();
        } else {
            throw new DataNotFoundException("releasemovie not found");
        }
    }
    
    public void create(String subject, SiteUser user) {
    	try {
    		ReleaseMovie q = new ReleaseMovie();
            q.setSubject(subject);
            q.setCreateDate(LocalDateTime.now());
            q.setAuthor(user);
            this.releasemMovieRepository.save(q);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        
    }

	public void modify(ReleaseMovie releaseMovie, String subject) {
		releaseMovie.setSubject(subject);
		releaseMovie.setModifyDate(LocalDateTime.now());
		this.releasemMovieRepository.save(releaseMovie); //수정한 내용을 다시 Question에 저장
		
	}

	public void delete(ReleaseMovie releaseMovie) {
		this.releasemMovieRepository.delete(releaseMovie);
		
	}
}
