package com.ll.wisesaying.domain.repository;

import com.ll.wisesaying.domain.entity.WiseSaying;
import com.ll.wisesaying.global.utils.FileUtil;
import com.ll.wisesaying.global.utils.JsonUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

import static com.ll.wisesaying.global.config.FileConfig.*;

public class WiseSayingRepository {

    private final ArrayList<WiseSaying> wiseSayings;
    private int lastIdx;

    public WiseSayingRepository() {
        this.wiseSayings = new ArrayList<>();
    }

    public void initWiseSayings() {

        // DB 파일 저장할 디렉토리 설정
        if (!FileUtil.checkFileDirExist(DEFAULT_DB_PATH)) {
            FileUtil.createFileDir(DEFAULT_DB_PATH);
        }

        // 마지막 명언 Idx 탐색
        int lastWiseSayingIdx = FileUtil.getIntInFile(LAST_ID_FILE);

        // 명언 Idx 파일을 순회하여, 존재하는 명언 목록들을 list에 추가
        for (int idx = 1; idx <= lastWiseSayingIdx; idx++) {

            FileUtil.getStringInFile(idx + ".json").ifPresent(jsonData -> {
                WiseSaying wiseSaying = parseWiseSayingInJson(jsonData);
                savedWiseSaying(wiseSaying.getContent(), wiseSaying.getAuthor(), false);
            });
        }
    }

    public int savedWiseSaying(String content, String author, boolean isFileSaved) {

        int lastWiseSayingIdx = findLastWiseSayingIdx();
        WiseSaying saved = new WiseSaying(++lastWiseSayingIdx, content, author);

        // List에 저장
        wiseSayings.add(saved);
        lastIdx = lastWiseSayingIdx;

        if (isFileSaved) {
            // 명언 idx에 해당하는 파일에 명언 정보 저장
            String fileName = lastWiseSayingIdx + ".json";
            FileUtil.writeStringInFile(fileName,
                    makeJsonString(lastWiseSayingIdx, saved.getContent(), saved.getAuthor()));

            // 마지막 명언 idx 저장 파일에 명언 idx 저장
            FileUtil.writeIntInFile(LAST_ID_FILE, lastWiseSayingIdx);
        }

        return lastWiseSayingIdx;
    }

    public List<WiseSaying> findAllWiseSayings() {
        return wiseSayings;
    }

    public Optional<WiseSaying> findByWiseSayingIdx(int wiseSayingIdx) {
        return wiseSayings.stream().filter(wiseSaying -> wiseSaying.getIdx() == wiseSayingIdx).findFirst();
    }

    public boolean deleteWiseSaying(int wiseSayingIdx) {

        boolean isDeleted = false;

        if (wiseSayings.removeIf(wiseSaying -> wiseSaying.getIdx() == wiseSayingIdx)) {

            FileUtil.deleteFile(wiseSayingIdx + ".json");
            isDeleted = true;
        }

        return isDeleted;
    }

    public void updateWiseSaying(WiseSaying wiseSaying) {

        IntStream.range(0, wiseSayings.size())
                .filter(i -> wiseSayings.get(i).getIdx() == wiseSaying.getIdx())
                .findFirst()
                .ifPresent(i -> wiseSayings.set(i, wiseSaying));

        // 명언 idx에 해당하는 파일에 명언 정보 저장
        String update = makeJsonString(wiseSaying.getIdx(), wiseSaying.getContent(), wiseSaying.getAuthor());
        FileUtil.writeStringInFile(wiseSaying.getIdx() + ".json", update);
    }

    public void updateAllWiseSayings() {

        // 명언 list에 존재하는 모든 명언을 json 파일로 합치기
        FileUtil.writeStringInFile(BUILD_FILE, makeJsonArray(wiseSayings));
    }

    public int findLastWiseSayingIdx() {
        return lastIdx;
    }

    private WiseSaying parseWiseSayingInJson(String jsonString) {

        int idx = 0;
        String content = null, author = null;

        StringTokenizer st = new StringTokenizer(jsonString, "{,}");
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            String key = JsonUtil.parseJsonKey(token);

            switch (key) {
                case "id" -> idx = JsonUtil.parseIntJsonValue(token);
                case "content" -> content = JsonUtil.parseStringJsonValue(token);
                case "author" -> author = JsonUtil.parseStringJsonValue(token);
            }
        }

        return new WiseSaying(idx, content, author);
    }

    private String makeJsonArray(List<WiseSaying> wiseSayingList) {

        StringBuilder sb = new StringBuilder();

        sb.append("[\n");

        for (int idx = 0; idx < wiseSayingList.size(); idx++) {

            WiseSaying wiseSaying = wiseSayingList.get(idx);

            sb.append("\t");
            sb.append(makeJsonString(wiseSaying.getIdx(), wiseSaying.getContent(), wiseSaying.getAuthor()));
            if (idx != wiseSayingList.size() - 1) {
                sb.append(",");
            }
        }

        sb.append("]");

        return sb.toString();
    }

    private String makeJsonString(int wiseSayingIdx, String wiseSayingContent, String wiseSayingAuthor) {

        return String.format("""
                {
                    %s,
                    %s,
                    %s
                }""",
                JsonUtil.createIntJson("id", wiseSayingIdx),
                JsonUtil.createStringJson("content", wiseSayingContent),
                JsonUtil.createStringJson("author", wiseSayingAuthor));
    }
}
