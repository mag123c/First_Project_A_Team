package com.example.board;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.review.mrc.Mrc1;
import com.example.board.review.mrc.MrcRepository;

@SpringBootTest
class AteamApplicationTests {

    @Autowired
    private MrcRepository mrcRepository;

    @Test
    void testJpa() {        
        Mrc1 m1 = new Mrc1();
        m1.setContent("ㅎㅇ");
        m1.setCreateDate(LocalDateTime.now());
        this.mrcRepository.save(m1);  

        Mrc1 m2 = new Mrc1();
        m2.setContent("qd");
        m2.setCreateDate(LocalDateTime.now());
        this.mrcRepository.save(m2);  
    }

}
