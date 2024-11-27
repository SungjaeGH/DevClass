package com.ll.wisesaying.entity;

public class WiseSaying {

    private final int idx;
    private String content;
    private String author;

    public WiseSaying(int idx, String content, String author) {
        this.idx = idx;
        this.content = content;
        this.author = author;
    }

    public int getIdx() {
        return idx;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
