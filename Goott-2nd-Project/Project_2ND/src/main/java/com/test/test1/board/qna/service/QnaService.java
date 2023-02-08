package com.test.test1.board.qna.service;

import java.util.List;

import com.test.test1.board.qna.dto.QnaDto;

public interface QnaService {

	void qCreate(QnaDto qnaDto);

	List<QnaDto> list();

	QnaDto detail(int question_id);

	void modify(QnaDto qnaDto);

	void delete(int question_id);

	List<QnaDto> qnaSearch(QnaDto qnaDto);
	
}
