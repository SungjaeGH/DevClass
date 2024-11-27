package com.ll.wisesaying.dto;

import com.ll.wisesaying.entity.WiseSaying;

public class RequestUpdateWiseSaying {

    private final int updateIdx;
    private final String updateContent;
    private final String updateAuthor;

    public RequestUpdateWiseSaying(int updateIdx, String updateContent, String updateAuthor) {
        this.updateIdx = updateIdx;
        this.updateContent = updateContent;
        this.updateAuthor = updateAuthor;
    }

    public int getUpdateIdx() {
        return updateIdx;
    }

    public String getUpdateContent() {
        return updateContent;
    }

    public String getUpdateAuthor() {
        return updateAuthor;
    }

    public WiseSaying toEntity() {
        return new WiseSaying(
                this.updateIdx,
                this.updateContent,
                this.updateAuthor
        );
    }
}
