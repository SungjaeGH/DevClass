package com.ll.wisesaying.repository;

import com.ll.wisesaying.entity.WiseSaying;

import java.util.ArrayList;

public class WiseSayingRepository {

    private final ArrayList<WiseSaying> wiseSayings;

    public WiseSayingRepository() {
        this.wiseSayings = new ArrayList<>();
    }

    public void savedWiseSaying(WiseSaying wiseSaying) {
        this.wiseSayings.add(wiseSaying);
    }

    public int findLastWiseSayingIdx() {
        return this.wiseSayings.size();
    }

    public boolean findByWishSayingIdx() {

    }


}
