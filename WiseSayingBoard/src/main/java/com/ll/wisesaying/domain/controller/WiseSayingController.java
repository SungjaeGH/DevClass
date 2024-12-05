package com.ll.wisesaying.domain.controller;

import com.ll.wisesaying.domain.dto.RequestFindWiseSayingByOption;
import com.ll.wisesaying.domain.dto.RequestRegisterWiseSaying;
import com.ll.wisesaying.domain.dto.RequestUpdateWiseSaying;
import com.ll.wisesaying.domain.dto.ResponseFindWiseSayings;
import com.ll.wisesaying.domain.service.WiseSayingService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.ll.wisesaying.global.config.CommandConfig.*;

public class WiseSayingController {

    private final WiseSayingService wiseSayingService;

    public WiseSayingController(WiseSayingService wiseSayingService) {
        this.wiseSayingService = wiseSayingService;
    }

    public void initWiseSayings() {
        wiseSayingService.initWiseSayings();
    }

    public void registerWiseSaying(BufferedReader bufferedReader) {

        try {
            System.out.print("명언 : ");
            String content = bufferedReader.readLine();

            System.out.print("작가 : ");
            String author = bufferedReader.readLine();

            RequestRegisterWiseSaying request = new RequestRegisterWiseSaying(content, author);
            int wiseSayingIdx = wiseSayingService.storedWiseSaying(request);

            System.out.println(wiseSayingIdx + "번 명언이 등록되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showWiseSayings(String command) {

        ResponseFindWiseSayings response;

        if (command.contains("?")) {

            Map<String, String> keywordGroup = parseKeywordGroup(command);
            RequestFindWiseSayingByOption request = new RequestFindWiseSayingByOption();

            if (keywordGroup.containsKey(COMMAND_SHOW_OPTION_KEYWORD_TYPE)) {
                request.setKeywordType(keywordGroup.get(COMMAND_SHOW_OPTION_KEYWORD_TYPE));
                System.out.println("-------------------------");
                System.out.println("검색타입 : " + request.getKeywordType());
            }

            if (keywordGroup.containsKey(COMMAND_SHOW_OPTION_KEYWORD)) {
                request.setKeyword(keywordGroup.get(COMMAND_SHOW_OPTION_KEYWORD));
                System.out.println("검색어 : " + request.getKeyword());
                System.out.println("-------------------------");
            }

            if (keywordGroup.containsKey(COMMAND_SHOW_OPTION_PAGE)) {
                request.setPageIdx(Integer.parseInt(keywordGroup.get(COMMAND_SHOW_OPTION_PAGE)));
            }

            response = wiseSayingService.findWiseSayings(request);

        } else {
            response = wiseSayingService.findWiseSayings();

        }

        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------------");
        System.out.print(response.wiseSayings());
        System.out.println("-------------------------");
        System.out.println("페이지 : " + makePagePrint(response));
    }

    public void deleteWiseSaying(String command) {

        int wiseSayingIdx = 0;
        Map<String, String> keywordGroup = parseKeywordGroup(command);

        if (keywordGroup.containsKey(COMMAND_OPTION_ID)) {
            wiseSayingIdx = Integer.parseInt(keywordGroup.get(COMMAND_OPTION_ID));
        }

        if (wiseSayingService.removeWiseSaying(wiseSayingIdx)) {
            System.out.println(wiseSayingIdx + "번 명언이 삭제되었습니다.");

        } else {
            System.out.println(wiseSayingIdx + "번 명언은 존재하지 않습니다.");

        }
    }

    public void updateWiseSaying(String command, BufferedReader bufferedReader) {

        int wiseSayingIdx;
        Map<String, String> keywordGroup = parseKeywordGroup(command);

        if (keywordGroup.containsKey(COMMAND_OPTION_ID)) {
            wiseSayingIdx = Integer.parseInt(keywordGroup.get(COMMAND_OPTION_ID));

        } else {
            wiseSayingIdx = 0;
        }

        wiseSayingService.checkedWiseSayingExist(wiseSayingIdx).ifPresentOrElse(response -> {

            try {
                System.out.println("명언(기존) : " + response.getContent());
                System.out.print("명령 : ");
                String updateContent = bufferedReader.readLine();

                System.out.println("작가(기존) : " + response.getAuthor());
                System.out.print("명령 : ");
                String updateAuthor = bufferedReader.readLine();

                RequestUpdateWiseSaying request = new RequestUpdateWiseSaying(wiseSayingIdx, updateContent, updateAuthor);
                wiseSayingService.updateWiseSaying(request);

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, () -> System.out.println(wiseSayingIdx + "번 명언은 존재하지 않습니다."));
    }

    public void buildWiseSayings() {

        wiseSayingService.updateAllWiseSayings();
        System.out.println("data.json 파일의 내용이 갱신되었습니다.");
    }

    private Map<String, String> parseKeywordGroup(String command) {

        Map<String, String> keywordGroup = new HashMap<>();
        boolean isEnd = false;
        String remainKeyword = command.substring(command.indexOf("?") + 1);

        while (!isEnd) {

            String targetKeyword;
            int nextKeywordIdx = remainKeyword.indexOf("&");
            if (nextKeywordIdx == -1) {
                isEnd = true;
                targetKeyword = remainKeyword;

            } else {
                targetKeyword = remainKeyword.substring(0, nextKeywordIdx);

            }

            String key = parseKeywordKey(targetKeyword);
            String value = parseKeywordValue(targetKeyword);

            keywordGroup.put(key, value);
            remainKeyword = remainKeyword.substring(nextKeywordIdx + 1);
        }

        return keywordGroup;
    }

    private String parseKeywordKey(String content) {
        return content.substring(0, content.indexOf("="));
    }

    private String parseKeywordValue(String content) {
        return content.substring(content.indexOf("=") + 1);
    }

    private StringBuilder makePagePrint(ResponseFindWiseSayings response) {

        StringBuilder sb = new StringBuilder();

        for (int pageIdx = 1; pageIdx <= response.pageMax(); pageIdx++) {

            if (pageIdx == response.pageNum()) {
                sb.append("[").append(pageIdx).append("]");

            } else {
                sb.append(pageIdx);
            }

            if (pageIdx != response.pageMax()) {
                sb.append(" / ");
            }
        }

        return sb;
    }
}
