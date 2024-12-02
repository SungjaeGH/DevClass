package com.ll.wisesaying.domain.service;

import com.ll.wisesaying.domain.dto.RequestFindWiseSayingByKeyword;
import com.ll.wisesaying.domain.dto.RequestRegisterWiseSaying;
import com.ll.wisesaying.domain.dto.RequestUpdateWiseSaying;
import com.ll.wisesaying.domain.dto.ResponseUpdateWiseSaying;
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

    public String findWiseSayings() {

        StringBuilder sb = new StringBuilder();

        List<WiseSaying> wiseSayings = wiseSayingRepository.findAllWiseSayings();
        wiseSayings.forEach(wiseSaying ->
                sb.append(String.format("%d / %s / %s\n",
                        wiseSaying.getIdx(), wiseSaying.getAuthor(), wiseSaying.getContent())));

        return sb.toString();
    }

    public String findWiseSayings(RequestFindWiseSayingByKeyword request) {

        StringBuilder sb = new StringBuilder();

        List<WiseSaying> wiseSayings = wiseSayingRepository.findAllWiseSayingsByKeyword(request.type(), request.value());
        wiseSayings.forEach(wiseSaying ->
                sb.append(String.format("%d / %s / %s\n",
                        wiseSaying.getIdx(), wiseSaying.getAuthor(), wiseSaying.getContent())));

        return sb.toString();
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
