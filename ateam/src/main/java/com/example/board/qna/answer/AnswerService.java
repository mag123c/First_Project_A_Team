package com.example.board.qna.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.example.board.qna.Question;
import com.example.board.user.SiteUser;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;


    public Answer create(Question question , String content) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        
        this.answerRepository.save(answer);
        return answer;
    }
    public void modify(Answer answer, String content) {
   	 answer.setContent(content);
   	 answer.setModifyDate(LocalDateTime.now());
   	 this.answerRepository.save(answer);
    }
}