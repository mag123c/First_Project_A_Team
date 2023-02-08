package com.test.test1.board.qna.dto;

import java.util.Date;

public class QnaDto {

	private int question_id, user_id;
	private String q_subject, q_content, answer;
	private Date q_create_date, a_create_date;
	private String nickname;
	private String keyword, option;
	
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getOption() {
		return option;
	}
	public void setOption(String option) {
		this.option = option;
	}
	public int getQuestion_id() {
		return question_id;
	}
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getQ_subject() {
		return q_subject;
	}
	public void setQ_subject(String q_subject) {
		this.q_subject = q_subject;
	}
	public String getQ_content() {
		return q_content;
	}
	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}
	public Date getQ_create_date() {
		return q_create_date;
	}
	public void setQ_create_date(Date q_create_date) {
		this.q_create_date = q_create_date;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getA_create_date() {
		return a_create_date;
	}
	public void setA_create_date(Date a_create_date) {
		this.a_create_date = a_create_date;
	}
	
	@Override
	public String toString() {
		return "QnaDto : [question_id=" + question_id + ",user_id="+ user_id + ",nickname="+nickname+", q_subject=" + q_subject + ", q_content=" + q_content + 
				", q_create_date=" + q_create_date + ", answer=" + answer + ", a_create_date=" + a_create_date + "]";
	}
	
	//게시판 목록 조회 시 userKey에 대한 nickname을 담음 - 02.07 장재호
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}	
}
