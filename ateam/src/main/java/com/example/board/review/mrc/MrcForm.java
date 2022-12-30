package com.example.board.review.mrc;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MrcForm {

	@NotEmpty(message="입력하세요")
	@Size(max=100)
	private String content;

}
