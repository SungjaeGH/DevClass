package com.ll.wisesaying;

import com.ll.wisesaying.controller.WiseSayingController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WiseSayingApp {

    private final WiseSayingController controller;

    public WiseSayingApp(WiseSayingController controller) {
        this.controller = controller;
    }

    public void run() {

        /* 아래 부분들은 더 낮은 수준에서 구현하는게 맞을듯 */
//        ArrayList<WishSaying> wishSayings = new ArrayList<>();
//        FileUtil fileUtil = new FileUtil();
//
//        // 마지막 명언 Idx 탐색
//        fileUtil.setFileDir("lastId.txt");
//        int wishSayingIdx = fileUtil.findLastWishSayingIdx();
//
//        // 명언 Idx 파일을 순회하여, 존재하는 명언 목록들을 list에 추가
//        for (int idx = 1; idx <= wishSayingIdx; idx++) {
//
//            fileUtil.setFileDir(idx + ".json");
//            WishSaying wishSaying = fileUtil.findWishSaying();
//            if (wishSaying != null) {
//                wishSayings.add(wishSaying);
//            }
//        }

        System.out.println("== 명언 앱 ==");

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            boolean isEnd = false;
            while (!isEnd) {

                System.out.print("명령) ");
                String command = bufferedReader.readLine();

                if (command.equals("종료")) {

                    isEnd = true;

                } else if (command.equals("등록")) {

                    controller.registerWiseSaying(bufferedReader);

                } else if (command.equals("목록")) {
                    controller.showWiseSayings();

                } else if (command.contains("삭제")) {
                    controller.deleteWiseSaying(command);

                } else if (command.contains("수정")) {
                    controller.updateWiseSaying(command);

                } else if (command.equals("빌드")) {
                    controller.buildWiseSayings();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
