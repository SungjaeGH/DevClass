package com.ll.wisesaying.controller;

import com.ll.wisesaying.dto.RequestRegisterWiseSaying;
import com.ll.wisesaying.dto.RequestUpdateWiseSaying;
import com.ll.wisesaying.service.WiseSayingService;

import java.io.BufferedReader;

public class WiseSayingController {

    private final WiseSayingService wiseSayingService;

    public WiseSayingController(WiseSayingService wiseSayingService) {
        this.wiseSayingService = wiseSayingService;
    }

    public void registerWiseSaying(BufferedReader bufferedReader) {

        try {

            System.out.print("명언 : ");
            String content = bufferedReader.readLine();

            System.out.print("작가 : ");
            String author = bufferedReader.readLine();

            RequestRegisterWiseSaying request = new RequestRegisterWiseSaying(content, author);
            int wishSayingIdx = wiseSayingService.storedWishSaying(request);

            System.out.println(wishSayingIdx + "번 명언이 등록되었습니다.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showWiseSayings() {

        System.out.println("번호 / 작가 / 명언");
        System.out.println("-------------------------");

        String wishSayings = wiseSayingService.findWishSayings();
        System.out.println(wishSayings);
    }

    public void deleteWiseSaying(String command) {

        String wiseSayingIdxStr = command.substring(command.indexOf('=') + 1);

        if (wiseSayingService.removeWiseSaying(Integer.parseInt(wiseSayingIdxStr))) {
            System.out.println(wiseSayingIdxStr + "번 명령이 삭제되었습니다.");

        } else {
            System.out.println(wiseSayingIdxStr + "번 명언은 존재하지 않습니다.");

        }
    }

    public void updateWiseSaying(String command) {

        String wiseSayingIdxStr = command.substring(command.indexOf('=') + 1);
        int wiseSayingIdx = Integer.parseInt(wiseSayingIdxStr);

        wiseSayingService.checkedWiseSayingExist(wiseSayingIdx).ifPresentOrElse(
                response -> {
                    RequestUpdateWiseSaying request = new RequestUpdateWiseSaying(response.idx(), response.content(), response.author());
                    wiseSayingService.updateWiseSaying(request);

                },
                () -> {
                    System.out.println(wiseSayingIdx + "번 명언은 존재하지 않습니다.");
                }
        );
    }

    public void buildWiseSayings() {

        wiseSayingService.updateAllWiseSayings();
        System.out.println("data.json 파일의 내용이 갱신되었습니다.");
    }
}
