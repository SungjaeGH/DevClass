package com.ll.wisesaying.domain.controller;

import com.ll.wisesaying.domain.dto.RequestFindWiseSayingByKeyword;
import com.ll.wisesaying.domain.dto.RequestRegisterWiseSaying;
import com.ll.wisesaying.domain.dto.RequestUpdateWiseSaying;
import com.ll.wisesaying.domain.service.WiseSayingService;

import java.io.BufferedReader;
import java.io.IOException;

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

        String wiseSayings;

        if (command.contains("keyword")) {
            String[] keywordTypeGroup = parseKeywordGroup(command.substring(command.indexOf("?") + 1, command.indexOf("&")));
            String[] keywordGroup = parseKeywordGroup(command.substring(command.indexOf("&") + 1));

            RequestFindWiseSayingByKeyword request = new RequestFindWiseSayingByKeyword(keywordTypeGroup[1], keywordGroup[1]);
            System.out.println("-------------------------");
            System.out.println("검색타입 : " + request.type());
            System.out.println("검색어 : " + request.value());
            System.out.println("-------------------------");

            wiseSayings = wiseSayingService.findWiseSayings(request);

        } else {
            wiseSayings = wiseSayingService.findWiseSayings();

        }

        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------------");
        System.out.println(wiseSayings);
    }

    public void deleteWiseSaying(String command) {

        String[] keywordGroup = parseKeywordGroup(command.substring(command.indexOf("?") + 1));
        int wiseSayingIdx = Integer.parseInt(keywordGroup[1]);

        if (wiseSayingService.removeWiseSaying(wiseSayingIdx)) {
            System.out.println(wiseSayingIdx + "번 명언이 삭제되었습니다.");

        } else {
            System.out.println(wiseSayingIdx + "번 명언은 존재하지 않습니다.");

        }
    }

    public void updateWiseSaying(String command, BufferedReader bufferedReader) {

        String[] keywordGroup = parseKeywordGroup(command.substring(command.indexOf("?") + 1));
        int wiseSayingIdx = Integer.parseInt(keywordGroup[1]);

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

    private String[] parseKeywordGroup(String content) {
        return new String[]{parseKeywordKey(content), parseKeywordValue(content)};
    }

    private String parseKeywordKey(String content) {
        return content.substring(0, content.indexOf("="));
    }

    private String parseKeywordValue(String content) {
        return content.substring(content.indexOf("=") + 1);
    }
}
