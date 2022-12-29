package com.example.board.releasemovie;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

	public interface ReleasemMovieRepository extends JpaRepository<ReleaseMovie, Integer> {
		ReleaseMovie findBySubject(String subject);
		ReleaseMovie findBySubjectAndContent(String subject, String content);
	    List<ReleaseMovie> findBySubjectLike(String subject);
	    Page<ReleaseMovie> findAll(Pageable pageable);
	
}
