package com.ll.wisesaying.domain.dto;

import com.ll.wisesaying.domain.entity.WiseSaying;

public class ResponseUpdateWiseSaying {

    private int idx;
    private String content;
    private String author;

    public int getIdx() {
        return idx;
    }

    public String getContent() {
        return content;
    }

    public String getAuthor() {
        return author;
    }

    public void UpdateResponse(WiseSaying wiseSaying) {
        this.idx = wiseSaying.getIdx();
        this.content = wiseSaying.getContent();
        this.author = wiseSaying.getAuthor();
    }
}
