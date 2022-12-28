package com.example.board.review.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.board.review.Review;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;


    public void create(Review review, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setCreateDate(LocalDateTime.now());
        comment.setReview(review);
        this.commentRepository.save(comment);
    }
}