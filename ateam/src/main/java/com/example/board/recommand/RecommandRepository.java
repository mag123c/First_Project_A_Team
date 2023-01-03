package com.example.board.recommand;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RecommandRepository extends JpaRepository<Recommand, Integer>{

	
	
	
	
//	Page<Recommand> findAll(Pageable pageable);
//	
//	  @Query("select "
//	  + "distinct q "
//	  + "from Recommand q " 
//	  + "left outer join SiteUser u1 on q.author=u1 "
//	  + "where "
//	  + "   q.content like %:kw% "
//	  + "   or u1.username like %:kw% ")
//	Page<Recommand> findAllByKeyword(@Param("kw") String kw, Pageable pageable);
}
