package com.test.test1.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.test1.dao.UserDao;
import com.test.test1.dao.VideoDao;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDao userDao;
	
	@Override
	public String create(Map<String, Object> map) {
		int cnt = this.userDao.insert(map);
		if (cnt == 1) {
			return map.get("USER_ID").toString();
		}
		return null;
	}
	
}