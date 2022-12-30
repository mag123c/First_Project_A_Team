package com.example.board.notice;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    Notice findBySubject(String subject);
    Notice findBySubjectAndContent(String subject, String content);
    List<Notice> findBySubjectLike(String subject);
    Page<Notice> findAll(Pageable pageable);
}