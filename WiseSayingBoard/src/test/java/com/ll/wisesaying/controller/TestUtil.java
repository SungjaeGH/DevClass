package com.ll.wisesaying.controller;

import com.ll.wisesaying.domain.controller.WiseSayingController;
import com.ll.wisesaying.domain.repository.WiseSayingRepository;
import com.ll.wisesaying.domain.service.WiseSayingService;
import com.ll.wisesaying.global.config.FileConfig;
import com.ll.wisesaying.global.utils.FileUtil;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.ll.wisesaying.global.config.FileConfig.*;

public class TestUtil {

    public WiseSayingController wiseSayingController;
    public WiseSayingService wiseSayingService;
    public WiseSayingRepository wiseSayingRepository;

    public void init() {

        if (wiseSayingRepository == null) {
            wiseSayingRepository = new WiseSayingRepository();
        }

        if (wiseSayingService == null) {
            wiseSayingService = new WiseSayingService(wiseSayingRepository);
        }

        if (wiseSayingController == null) {
            wiseSayingController = new WiseSayingController(wiseSayingService);
        }

        if (FileUtil.checkFileDirExist(DEFAULT_TEST_RESOURCE_PATH + DEFAULT_DB_PATH)) {
            FileUtil.createFileDir(DEFAULT_TEST_RESOURCE_PATH + DEFAULT_DB_PATH);
        }
    }

    public void detach() throws IOException {

        Path path = Paths.get(FileConfig.DEFAULT_TEST_RESOURCE_PATH + "db");
        FileUtil.deleteDir(path);
    }

    // 테스트용 스캐너 생성
    public static BufferedReader generateScanner(final String input) {
        final InputStream in = new ByteArrayInputStream(input.getBytes());

        return new BufferedReader(new InputStreamReader(in));
    }

    // System.out의 출력을 스트림으로 받기
    public static ByteArrayOutputStream setOutToByteArray() {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        return output;
    }

    // setOutToByteArray 함수의 사용을 완료한 후 정리하는 함수, 출력을 다시 정상화 하는 함수
    public static void clearSetOutToByteArray(final ByteArrayOutputStream output) {
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        try {
            output.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
