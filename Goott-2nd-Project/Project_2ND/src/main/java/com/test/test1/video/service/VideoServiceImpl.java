package com.test.test1.video.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.test1.video.dao.VideoDao;

@Service
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	VideoDao videoDao;
	
	@Override
	public String create(Map<String, Object> map) {
		int cnt = this.videoDao.insert(map);
		if (cnt == 1) {
			return map.get("VIDEO_ID").toString();
		}
		return null;
	}
	@Override
	public Map<String, Object> detail(Map<String, Object> map){
	  
		return this.videoDao.selectDetail(map);
	}


}
