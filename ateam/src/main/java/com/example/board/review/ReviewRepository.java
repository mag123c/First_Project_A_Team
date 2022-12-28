package com.example.board.review;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Review findBySubject(String subject);
    Review findBySubjectAndContent(String subject, String content);
    List<Review> findBySubjectLike(String subject);
    Page<Review> findAll(Pageable pageable);
}