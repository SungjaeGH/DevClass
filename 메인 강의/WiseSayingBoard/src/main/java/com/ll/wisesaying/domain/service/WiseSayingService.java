package com.ll.wisesaying.domain.service;

import com.ll.wisesaying.domain.dto.*;
import com.ll.wisesaying.domain.entity.WiseSaying;
import com.ll.wisesaying.domain.repository.WiseSayingRepository;

import java.util.List;
import java.util.Optional;

public class WiseSayingService {

    private final WiseSayingRepository wiseSayingRepository;

    public WiseSayingService(WiseSayingRepository wiseSayingRepository) {
        this.wiseSayingRepository = wiseSayingRepository;
    }

    public void initWiseSayings() {
        wiseSayingRepository.initWiseSayings();
    }

    public int storedWiseSaying(RequestRegisterWiseSaying request) {
        return wiseSayingRepository.savedWiseSaying(request.content(), request.author(), true);
    }

    public ResponseFindWiseSayings findWiseSayings() {

        StringBuilder sb = new StringBuilder();

        List<WiseSaying> wiseSayings = wiseSayingRepository.findAllWiseSayings();
        List<WiseSaying> sortedList = wiseSayingRepository.findWiseSayingsByPagingDesc(wiseSayings, 1, 5);
        sortedList.forEach(wiseSaying ->
                sb.append(String.format("%d / %s / %s\n",
                        wiseSaying.getIdx(), wiseSaying.getAuthor(), wiseSaying.getContent())));

        return new ResponseFindWiseSayings(
                1,
                wiseSayings.isEmpty() ? 1 : (int) Math.ceil((double) wiseSayings.size() / 5),
                sb.toString());
    }

    public ResponseFindWiseSayings findWiseSayings(RequestFindWiseSayingByOption request) {

        StringBuilder sb = new StringBuilder();
        List<WiseSaying> wiseSayings;

        if (request.getKeyword() == null && request.getKeywordType() == null) {
            wiseSayings = wiseSayingRepository.findAllWiseSayings();

        } else {
            wiseSayings = wiseSayingRepository.findAllWiseSayingsByKeyword(request.getKeywordType(), request.getKeyword());
        }

        List<WiseSaying> sortedList = wiseSayingRepository.findWiseSayingsByPagingDesc(wiseSayings, request.getPageIdx(), 5);
        sortedList.forEach(wiseSaying ->
                sb.append(String.format("%d / %s / %s\n",
                        wiseSaying.getIdx(), wiseSaying.getAuthor(), wiseSaying.getContent())));

        int pageNum = request.getPageIdx();
        int pageMax = wiseSayings.isEmpty() ? 1 : (int) Math.ceil((double) wiseSayings.size() / 5);

        return new ResponseFindWiseSayings(
                (pageNum > pageMax) ? 1 : pageNum,
                pageMax,
                sb.toString());
    }

    public boolean removeWiseSaying(int wiseSayingIdx) {
        return wiseSayingRepository.deleteWiseSaying(wiseSayingIdx);
    }

    public Optional<ResponseUpdateWiseSaying> checkedWiseSayingExist(int wiseSayingIdx) {
        return wiseSayingRepository.findByWiseSayingIdx(wiseSayingIdx)
                .map(responseUpdateWiseSaying -> {
                    ResponseUpdateWiseSaying response = new ResponseUpdateWiseSaying();
                    response.UpdateResponse(responseUpdateWiseSaying);
                    return response;
                });
    }

    public void updateWiseSaying(RequestUpdateWiseSaying request) {
        wiseSayingRepository.updateWiseSaying(request.toEntity());
    }

    public void updateAllWiseSayings() {
        wiseSayingRepository.updateAllWiseSayings();
    }
}
