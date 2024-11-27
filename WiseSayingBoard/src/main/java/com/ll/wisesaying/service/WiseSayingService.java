package com.ll.wisesaying.service;


import com.ll.wisesaying.dto.RequestRegisterWiseSaying;
import com.ll.wisesaying.dto.RequestUpdateWiseSaying;
import com.ll.wisesaying.dto.ResponseUpdateWiseSaying;
import com.ll.wisesaying.entity.WiseSaying;
import com.ll.wisesaying.repository.WiseSayingRepository;
import com.ll.wisesaying.utils.FileUtil;
import com.ll.wisesaying.utils.JsonUtil;

import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

public class WiseSayingService {

    private final WiseSayingRepository wiseSayingRepository;
    private final FileUtil fileUtil;
    private final JsonUtil jsonUtil;

    public WiseSayingService(WiseSayingRepository wiseSayingRepository, FileUtil fileUtil, JsonUtil jsonUtil) {
        this.wiseSayingRepository = wiseSayingRepository;
        this.fileUtil = fileUtil;
        this.jsonUtil = jsonUtil;
    }

    public void initWiseSayings() {

        // 마지막 명언 Idx 탐색
        fileUtil.setFileName("lastId.txt");
        int lastWiseSayingIdx = fileUtil.getIntInFile();

        // 명언 Idx 파일을 순회하여, 존재하는 명언 목록들을 list에 추가
        for (int idx = 1; idx <= lastWiseSayingIdx; idx++) {

            fileUtil.setFileName(idx + ".json");
            fileUtil.getStringInFile().ifPresent(jsonData ->
                wiseSayingRepository.savedWiseSaying(parseWiseSayingInJson(jsonData))
            );
        }
    }

    public int storedWiseSaying(RequestRegisterWiseSaying request) {

        int lastWiseSayingIdx = wiseSayingRepository.findLastWiseSayingIdx();
        WiseSaying saved = new WiseSaying(++lastWiseSayingIdx, request.content(), request.author());

        // List에 저장
        wiseSayingRepository.savedWiseSaying(saved);

        // 명언 idx에 해당하는 파일에 명언 정보 저장
        fileUtil.setFileName(lastWiseSayingIdx + ".json");
        fileUtil.writeStringInFile(makeJsonString(lastWiseSayingIdx, saved.getContent(), saved.getAuthor()));

        // 마지막 명언 idx 저장 파일에 명언 idx 저장
        fileUtil.setFileName("lastId.txt");
        fileUtil.writeIntInFile(lastWiseSayingIdx);

        return lastWiseSayingIdx;
    }

    public String findWiseSayings() {

        StringBuilder sb = new StringBuilder();

        List<WiseSaying> wiseSayings = wiseSayingRepository.findAllWiseSayings();
        wiseSayings.forEach(wiseSaying -> sb.append(
                String.format("%d / %s / %s\n",
                        wiseSaying.getIdx(),
                        wiseSaying.getAuthor(),
                        wiseSaying.getContent()
                )
        ));

        return sb.toString();
    }

    public boolean removeWiseSaying(int wiseSayingIdx) {

        if (!wiseSayingRepository.deleteWiseSaying(wiseSayingIdx)) {
            return false;
        }

        // 명언 idx에 해당하는 파일 삭제
        fileUtil.setFileName(wiseSayingIdx + ".json");
        fileUtil.deleteFile();

        return true;
    }

    public Optional<ResponseUpdateWiseSaying> checkedWiseSayingExist(int wiseSayingIdx) {

        ResponseUpdateWiseSaying responseUpdateWiseSaying = new ResponseUpdateWiseSaying();

        wiseSayingRepository.findByWiseSayingIdx(wiseSayingIdx)
                .ifPresent(responseUpdateWiseSaying::UpdateResponse);

        return Optional.of(responseUpdateWiseSaying);
    }


    public void updateWiseSaying(RequestUpdateWiseSaying request) {

        wiseSayingRepository.updateWiseSaying(request.toEntity());

        // 명언 idx에 해당하는 파일에 명언 정보 저장
        fileUtil.setFileName(request.getUpdateIdx() + ".json");
        String update = makeJsonString(request.getUpdateIdx(), request.getUpdateContent(), request.getUpdateAuthor());
        fileUtil.writeStringInFile(update);
    }

    public void updateAllWiseSayings() {

        List<WiseSaying> wiseSayingList = wiseSayingRepository.findAllWiseSayings();

        // 명언 list에 존재하는 모든 명언을 json 파일로 합치기
        fileUtil.setFileName("data.json");
        fileUtil.writeStringInFile(makeJsonArray(wiseSayingList));
    }

    private WiseSaying parseWiseSayingInJson(String jsonString) {

        int idx = 0;
        String content = null, author = null;

        StringTokenizer st = new StringTokenizer(jsonString, "{,}");
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            String key = jsonUtil.parseJsonKey(token);

            switch (key) {
                case "id" -> idx = jsonUtil.parseIntJsonValue(token);
                case "content" -> content = jsonUtil.parseStringJsonValue(token);
                case "author" -> author = jsonUtil.parseStringJsonValue(token);
            }
        }

        return new WiseSaying(idx, content, author);
    }

    private String makeJsonArray(List<WiseSaying> wiseSayingList) {

        StringBuilder sb = new StringBuilder();
        sb.append("[\n");
        for (int idx = 0; idx < wiseSayingList.size(); idx++) {

            WiseSaying wiseSaying = wiseSayingList.get(idx);

            sb.append("\t");
            sb.append(makeJsonString(wiseSaying.getIdx(), wiseSaying.getContent(), wiseSaying.getAuthor()));
            if (idx != wiseSayingList.size() - 1) {
                sb.append(",");
            }

        }
        sb.append("]");

        return sb.toString();
    }

    private String makeJsonString(int wiseSayingIdx, String wiseSayingContent, String wiseSayingAuthor) {

        return String.format("""
                {
                    %s,
                    %s,
                    %s
                }""",
                jsonUtil.createIntJson("id", wiseSayingIdx),
                jsonUtil.createStringJson("content", wiseSayingContent),
                jsonUtil.createStringJson("author", wiseSayingAuthor)
        );
    }
    
    
}
