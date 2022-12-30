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
	@NotEmpty(message="ID : 3~20자")
	private String username;
	
	@NotEmpty(message="이메일을 입력하세요")
	@Email
	private String email;
	
	@NotEmpty(message="닉네임을 입력하세요")
	private String nickname;
	
	@NotEmpty(message="패스워드를 입력하세요")
	private String password1;
	
	@NotEmpty(message="패스워드를 입력하세요")
	private String password2;

	
}