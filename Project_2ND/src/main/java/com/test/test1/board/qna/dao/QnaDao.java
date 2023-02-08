package com.test.test1.board.qna.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.test.test1.board.qna.dto.QnaDto;

@Repository
public class QnaDao {

	@Autowired
	SqlSession ss;
	
	//게시판 리스트 - 02.07 장재호
	public List<QnaDto> list() {
		return ss.selectList("qna.q_list");
	}
	
	//질문 생성 - 02.07 장재호
	public void qCreate(QnaDto qnaDto) {
		ss.insert("qna.q_create", qnaDto);

	}

	public QnaDto detail(int question_id) {		
		return ss.selectOne("qna.detail", question_id);
	}

	public void modify(QnaDto qnaDto) {
		ss.update("qna.modify", qnaDto);
	}

	//삭제 - 02.07 장재호
	public void delete(int question_id) {
		ss.delete("qna.delete", question_id);		
//		//auto_increment 재정렬
//		int asc = ss.selectOne("qna.lastNum"); //asc : 가장 마지막 번호
//		System.out.println("      asc     =      "        + asc);
//		ss.update("qna.autoincrementReset", asc);
	}
	
}
