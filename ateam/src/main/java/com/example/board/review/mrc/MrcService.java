package com.example.board.review.mrc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.board.DataNotFoundException;
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
    
	public Mrc1 getMrc1(Integer id) {
		Optional<Mrc1> mrc1 = this.mrcRepository.findById(id);
		if (mrc1.isPresent()) {
			return mrc1.get();
        } else {
            throw new DataNotFoundException("댓글이 없습니다");
        }
    }

    public void modify(Mrc1 mrc1, String content) {
        mrc1.setContent(content);
        mrc1.setModifyDate(LocalDateTime.now());
        this.mrcRepository.save(mrc1);
    }
	
    public void delete(Mrc1 mrc1) {
        this.mrcRepository.delete(mrc1);
    }
	

	
	
	
	
}
