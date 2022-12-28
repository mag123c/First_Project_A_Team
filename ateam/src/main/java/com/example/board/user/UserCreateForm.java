package com.example.board.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

	@Size(min = 3, max =20)
	@NotEmpty(message="ID는 3~20글자로 입력하세요")
	private String username;
	
	@NotEmpty(message="이메일을 입력하세요")
	@Email
	private String email;
	
	@NotEmpty(message="닉네임을 입력하세요")
	private String nickname;
	
	@NotEmpty(message="비밀번호를 입력하세요")
	private String password1;
	
	@NotEmpty(message="비밀번호를 확인해주세요")
	private String password2;

	
}