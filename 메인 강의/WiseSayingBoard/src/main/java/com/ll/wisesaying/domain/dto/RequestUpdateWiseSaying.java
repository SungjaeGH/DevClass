package com.ll.wisesaying.domain.dto;

import com.ll.wisesaying.domain.entity.WiseSaying;

public record RequestUpdateWiseSaying(int updateIdx, String updateContent, String updateAuthor) {

    public WiseSaying toEntity() {
        return new WiseSaying(
                this.updateIdx,
                this.updateContent,
                this.updateAuthor
        );
    }
}
