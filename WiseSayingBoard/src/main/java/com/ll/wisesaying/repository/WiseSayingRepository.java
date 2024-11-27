package com.ll.wisesaying.repository;

import com.ll.wisesaying.entity.WiseSaying;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public class WiseSayingRepository {

    private final ArrayList<WiseSaying> wiseSayings;
    private int lastIdx;

    public WiseSayingRepository() {
        this.wiseSayings = new ArrayList<>();
    }

    public void savedWiseSaying(WiseSaying wiseSaying) {
        this.wiseSayings.add(wiseSaying);
        this.lastIdx = wiseSaying.getIdx();
    }

    public List<WiseSaying> findAllWiseSayings() {

        return wiseSayings;
    }

    public int findLastWiseSayingIdx() {
        return this.lastIdx;
    }

    public Optional<WiseSaying> findByWiseSayingIdx(int wiseSayingIdx) {

        return wiseSayings.stream()
                .filter(wiseSaying -> wiseSaying.getIdx() == wiseSayingIdx)
                .findFirst();
    }

    public boolean deleteWiseSaying(int wiseSayingIdx) {

        return wiseSayings.removeIf(wiseSaying -> wiseSaying.getIdx() == wiseSayingIdx);
    }

    public void updateWiseSaying(WiseSaying wiseSaying) {

        IntStream.range(0, wiseSayings.size())
                .filter(i -> wiseSayings.get(i).getIdx() == wiseSaying.getIdx())
                .findFirst()
                .ifPresent(i -> wiseSayings.set(i, wiseSaying));
    }
}
