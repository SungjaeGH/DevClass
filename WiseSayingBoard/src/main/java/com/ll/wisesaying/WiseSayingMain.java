package com.ll.wisesaying;

import com.ll.wisesaying.domain.controller.WiseSayingController;
import com.ll.wisesaying.domain.repository.WiseSayingRepository;
import com.ll.wisesaying.domain.service.WiseSayingService;

public class WiseSayingMain {
    public static void main(String[] args) {

        // 인스턴스 생성
        WiseSayingRepository wiseSayingRepository = new WiseSayingRepository();
        WiseSayingService wiseSayingService = new WiseSayingService(wiseSayingRepository);
        WiseSayingController controller = new WiseSayingController(wiseSayingService);
        WiseSayingApp app = new WiseSayingApp(controller);

        // 앱 실행
        app.run();
    }
}
