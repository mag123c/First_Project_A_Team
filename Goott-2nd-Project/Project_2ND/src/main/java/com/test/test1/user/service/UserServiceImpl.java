package com.test.test1.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.test1.user.dao.UserDao;
import com.test.test1.user.dto.UserDto;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	BCryptPasswordEncoder encoder;
	
	@Override
	public boolean create(UserDto userDto) {
		return userDao.create(userDto);
	}
	
	@Override
	public String login(UserDto userDto, BCryptPasswordEncoder encoder) {
		return userDao.login(userDto, encoder);
	}

	@Override
	public List<UserDto> list() {
		return userDao.list();		
	}

	@Override
	public String emailCheck(String email) {
		return userDao.emailCheck(email);
	}


	@Override
	public List<UserDto> mydetail(String user_email) {
		return userDao.mydetail(user_email);
	}

	@Override
	public int modifyDetail(UserDto userDto) {
		return userDao.modify_detail(userDto);
	}

	@Override
	public int userIdx(String user) {
		return userDao.userIdx(user);
	}
}






	
