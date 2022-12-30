package com.example.board.review.mrc;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.board.review.Review;
import com.example.board.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MrcService {

	private final MrcRepository mrcRepository;
	
	public List<Mrc1> getList(){
		return this.mrcRepository.findAll();
	}
	
	public void create(String content, SiteUser user) {
        Mrc1 m1 = new Mrc1();
        m1.setContent(content);
        m1.setCreateDate(LocalDateTime.now());
        m1.setAuthor(user);
        this.mrcRepository.save(m1);
    }
	
}
