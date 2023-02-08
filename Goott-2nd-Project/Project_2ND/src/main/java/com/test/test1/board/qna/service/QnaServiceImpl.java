package com.test.test1.board.qna.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.test1.board.qna.dao.QnaDao;
import com.test.test1.board.qna.dto.QnaDto;

@Service
public class QnaServiceImpl implements QnaService {

	@Autowired
	QnaDao qnaDao;
	
	@Override
	public List<QnaDto> list() {
		return qnaDao.list();
	}	
	
	@Override
	public void qCreate(QnaDto qnaDto) {
		qnaDao.qCreate(qnaDto);
	}

	@Override
	public QnaDto detail(int question_id) {
		return qnaDao.detail(question_id);
	}

	@Override
	public void modify(QnaDto qnaDto) {
		qnaDao.modify(qnaDto);
	}

	@Override
	public void delete(int question_id) {
		qnaDao.delete(question_id);
	}

	@Override
	public List<QnaDto> qnaSearch(QnaDto qnaDto) {
		return qnaDao.qnaSearch(qnaDto);
	}


}
