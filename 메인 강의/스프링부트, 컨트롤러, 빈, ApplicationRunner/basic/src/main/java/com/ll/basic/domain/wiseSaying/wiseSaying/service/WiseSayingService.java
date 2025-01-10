package com.ll.basic.domain.wiseSaying.wiseSaying.service;

import com.ll.basic.domain.wiseSaying.wiseSaying.entity.WiseSaying;
import com.ll.basic.domain.wiseSaying.wiseSaying.repository.WiseSayingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WiseSayingService {

    private final WiseSayingRepository wiseSayingRepository;

    public List<WiseSaying> findAll() {
        return wiseSayingRepository.findAll();
    }

    public WiseSaying write(String content, String author) {

        WiseSaying wiseSaying = WiseSaying.builder()
                .content(content)
                .author(author)
                .build();

        return wiseSayingRepository.save(wiseSaying);
    }

    public Optional<WiseSaying> findById(long id) {
        return wiseSayingRepository.findById(id);
    }

    public boolean delete(long id) {
        return wiseSayingRepository.deleteById(id);
    }

    public void modify(WiseSaying wiseSaying, String content, String author) {

        wiseSaying.setContent(content);
        wiseSaying.setAuthor(author);
    }
}
