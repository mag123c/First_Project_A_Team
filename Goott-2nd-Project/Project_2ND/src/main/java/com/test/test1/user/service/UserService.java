package com.test.test1.user.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.test.test1.user.dto.UserDto;

public interface UserService {


	boolean create(UserDto userDto);

	List<UserDto> list();

	String login(UserDto userDto, BCryptPasswordEncoder encoder);

	String emailCheck(String email);

	List<UserDto> mydetail(String user_email);

	int modifyDetail(UserDto userDto);

	int userIdx(String user);



}

