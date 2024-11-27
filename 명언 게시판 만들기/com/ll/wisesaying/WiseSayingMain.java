package com.ll.wisesaying;

import com.ll.wisesaying.controller.WiseSayingController;
import com.ll.wisesaying.repository.WiseSayingRepository;
import com.ll.wisesaying.service.WiseSayingService;
import com.ll.wisesaying.utils.FileUtil;
import com.ll.wisesaying.utils.JsonUtil;

public class WiseSayingMain {
    public static void main(String[] args) {

        // 인스턴스 생성
        FileUtil fileUtil = new FileUtil("명언 게시판 만들기\\db\\wishSaying\\");
        JsonUtil jsonUtil = new JsonUtil();
        WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();
        WiseSayingService wiseSayingService = new WiseSayingService(wiseSayingRepository, fileUtil, jsonUtil);
        WiseSayingController controller = new WiseSayingController(wiseSayingService);
        WiseSayingApp app = new WiseSayingApp(controller);

        // 앱 실행
        app.run();
    }
}
