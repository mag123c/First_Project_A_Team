package com.example.board.review.mrc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.board.DataNotFoundException;
import com.example.board.user.SiteUser;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class Mrc2Service {
	
	private final Mrc2Repository mrc2Repository;
	
	public List<Mrc2> getList(){
		return this.mrc2Repository.findAll();
	}	
	
	public void create(String content, SiteUser user) {
        Mrc2 m2 = new Mrc2();
        m2.setContent(content);
        m2.setCreateDate(LocalDateTime.now());
        m2.setAuthor(user);
        this.mrc2Repository.save(m2);
    }
    
	public Mrc2 getMrc(Integer id) {
		Optional<Mrc2> mrc2 = this.mrc2Repository.findById(id);
		if (mrc2.isPresent()) {
			return mrc2.get();
        } else {
            throw new DataNotFoundException("댓글이 없습니다");
        }
    }

    public void modify(Mrc2 mrc2, String content) {
        mrc2.setContent(content);
        mrc2.setModifyDate(LocalDateTime.now());
        this.mrc2Repository.save(mrc2);
    }
	
    public void delete(Mrc2 mrc2) {
        this.mrc2Repository.delete(mrc2);
    }
}
