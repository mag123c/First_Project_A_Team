package com.example.board.notice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.board.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    public Page<Notice> getList(int page) {
    	List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("createDate"));
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
        return this.noticeRepository.findAll(pageable);
    }
    
    public Notice getNotice(Integer id) {  
        Optional<Notice> notice = this.noticeRepository.findById(id);
        if (notice.isPresent()) {
            return notice.get();
        } else {
            throw new DataNotFoundException("notice not found");
        }
    }
    
    public void create(String subject, String content) {
        Notice q = new Notice();
        q.setSubject(subject);
        q.setContent(content);
        q.setCreateDate(LocalDateTime.now());
        this.noticeRepository.save(q);
    }
}