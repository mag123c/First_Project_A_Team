package com.example.board.recommand;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.board.DataNotFoundException;
import com.example.board.review.mrc.Mrc2;
import com.example.board.user.SiteUser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecommandService {

	private final RecommandRepository recommandRepository;
	
	public List<Recommand> getList() {
        return this.recommandRepository.findAll();
    }
	
	public void create(String content, SiteUser siteuser) {
		// TODO Auto-generated method stub
		Recommand q1 = new Recommand();

		q1.setContent(content);
		q1.setCreateDate(LocalDateTime.now());
		q1.setAuthor(siteuser);

		this.recommandRepository.save(q1);	
	}




	public Recommand getRecommand(Integer id) {
		Optional<Recommand> recommand = this.recommandRepository.findById(id);
		if (recommand.isPresent()) {
			return recommand.get();
	    } else {
	        throw new DataNotFoundException("댓글이 없습니다");
	    }
	}


	
	// 수정관련 메소드
	  public void modify(Recommand recommand, String content) {
		  recommand.setContent(content);
		  recommand.setModifyDate(LocalDateTime.now());
	        this.recommandRepository.save(recommand);
	    }
	
  // 삭제 관련 메소드 
	   public void delete(Recommand recommand) {
	        this.recommandRepository.delete(recommand);
	    }

}
