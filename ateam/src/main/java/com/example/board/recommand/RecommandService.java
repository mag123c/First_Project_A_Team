package com.example.board.recommand;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RecommandService {

	private RecommandRepository recommandRepository;

	public Page<Recommand> getList(int page, String kw) { // 파라미터( : 게시글번호, 키워드) 받기.
	    	List<Sort.Order> sorts = new ArrayList<>(); 
	        sorts.add(Sort.Order.desc("createDate")); // 게시글 작성일자 순으로 내림차순 받기. 
	      
	        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
	        //Specification<Recommand> spec = search(kw); 
			return  this.recommandRepository.findAllByKeyword(kw, pageable);
	    }


}
