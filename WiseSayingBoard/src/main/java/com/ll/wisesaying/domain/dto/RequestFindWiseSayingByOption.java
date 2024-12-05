package com.ll.wisesaying.domain.dto;

public class RequestFindWiseSayingByOption {

    private String keywordType;
    private String keyword;
    private int pageIdx;

    public RequestFindWiseSayingByOption() {
        this.pageIdx = 1;
    }

    public String getKeywordType() {
        return keywordType;
    }

    public void setKeywordType(String keywordType) {
        this.keywordType = keywordType;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPageIdx() {
        return pageIdx;
    }

    public void setPageIdx(int pageIdx) {
        this.pageIdx = pageIdx;
    }
}
