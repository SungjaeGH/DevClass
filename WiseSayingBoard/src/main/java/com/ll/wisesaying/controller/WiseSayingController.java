package com.ll.wisesaying.controller;

import com.ll.wisesaying.dto.RequestRegisterWiseSaying;
import com.ll.wisesaying.dto.RequestUpdateWiseSaying;
import com.ll.wisesaying.service.WiseSayingService;

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

    public void showWiseSayings() {

        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------------");

        String wiseSayings = wiseSayingService.findWiseSayings();
        System.out.println(wiseSayings);
    }

    public void deleteWiseSaying(String command) {

        String wiseSayingIdxStr = command.substring(command.indexOf('=') + 1);

        if (wiseSayingService.removeWiseSaying(Integer.parseInt(wiseSayingIdxStr))) {
            System.out.println(wiseSayingIdxStr + "번 명령이 삭제되었습니다.");

        } else {
            System.out.println(wiseSayingIdxStr + "번 명언은 존재하지 않습니다.");

        }
    }

    public void updateWiseSaying(String command, BufferedReader bufferedReader) {

        String wiseSayingIdxStr = command.substring(command.indexOf('=') + 1);
        int wiseSayingIdx = Integer.parseInt(wiseSayingIdxStr);

        wiseSayingService.checkedWiseSayingExist(wiseSayingIdx).ifPresentOrElse(
                response -> {

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
                },
                () -> System.out.println(wiseSayingIdx + "번 명언은 존재하지 않습니다.")
        );
    }

    public void buildWiseSayings() {

        wiseSayingService.updateAllWiseSayings();
        System.out.println("data.json 파일의 내용이 갱신되었습니다.");
    }
}
