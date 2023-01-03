package com.example.board.recommand;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.board.user.SiteUser;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Recommand {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id; // 코멘트 고유번호 (wapper클래스 Integer)
	
	@Column(columnDefinition = "TEXT")
	private String content; // 코멘트 내용
	
	private LocalDateTime createDate; 
	
	
	 private LocalDateTime modifyDate; // 수정
	
	@ManyToOne
	private SiteUser author; 

}
