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

        System.out.println("== 명언 앱 ==");

        controller.initWiseSayings();

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
                    controller.updateWiseSaying(command, bufferedReader);

                } else if (command.equals("빌드")) {
                    controller.buildWiseSayings();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
