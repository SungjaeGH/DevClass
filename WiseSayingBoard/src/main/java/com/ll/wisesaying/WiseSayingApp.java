package com.ll.wisesaying;

import com.ll.wisesaying.domain.controller.WiseSayingController;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.ll.wisesaying.global.config.CommandConfig.*;

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

                if (command.equals(COMMAND_END)) {

                    isEnd = true;

                } else if (command.equals(COMMAND_REGISTER)) {

                    controller.registerWiseSaying(bufferedReader);

                } else if (command.contains(COMMAND_SHOW)) {
                    controller.showWiseSayings(command);

                } else if (command.contains(COMMAND_DELETE)) {
                    controller.deleteWiseSaying(command);

                } else if (command.contains(COMMAND_UPDATE)) {
                    controller.updateWiseSaying(command, bufferedReader);

                } else if (command.equals(COMMAND_BUILD)) {
                    controller.buildWiseSayings();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
