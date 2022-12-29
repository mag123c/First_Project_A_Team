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
    
    public void create(String subject, String content, String name, String date) {
    	try {
    		ReleaseMovie q = new ReleaseMovie();
            q.setSubject(subject);
            q.setContent(content);
            q.setName(name);
            q.setDate(date);
            q.setCreateDate(LocalDateTime.now());
            this.releasemMovieRepository.save(q);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
        
    }
}
