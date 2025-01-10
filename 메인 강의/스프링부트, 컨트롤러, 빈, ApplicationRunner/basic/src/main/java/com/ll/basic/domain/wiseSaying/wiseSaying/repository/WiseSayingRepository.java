package com.ll.basic.domain.wiseSaying.wiseSaying.repository;

import com.ll.basic.domain.wiseSaying.wiseSaying.entity.WiseSaying;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class WiseSayingRepository {

    private final List<WiseSaying> wiseSayings;
    private long lastId = 0;

    public List<WiseSaying> findAll() {
        return wiseSayings.stream()
                .sorted(Comparator.comparing(WiseSaying::getId).reversed())
                .toList();
    }

    public WiseSaying save(WiseSaying saved) {
        if (saved.isNew()) {
            saved.setId(++lastId);
            wiseSayings.add(saved);
        }

        return saved;
    }

    public Optional<WiseSaying> findById(long id) {
        return wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getId() == id)
                .findFirst();
    }

    public boolean deleteById(long id) {
        return wiseSayings.removeIf(wiseSaying -> wiseSaying.getId() == id);
    }
}
