package com.ll.wisesaying.service;


import com.ll.wisesaying.dto.RequestRegisterWiseSaying;
import com.ll.wisesaying.dto.RequestUpdateWiseSaying;
import com.ll.wisesaying.dto.ResponseUpdateWiseSaying;
import com.ll.wisesaying.entity.WiseSaying;
import com.ll.wisesaying.repository.WiseSayingRepository;
import com.ll.wisesaying.utils.FileUtil;
import com.ll.wisesaying.utils.JsonUtil;

import java.util.Optional;

public class WiseSayingService {

    /* 순수 비지니스 로직 */

    private final WiseSayingRepository wiseSayingRepository;
    private final FileUtil fileUtil;
    private final JsonUtil jsonUtil;

    public WiseSayingService(WiseSayingRepository wiseSayingRepository, FileUtil fileUtil, JsonUtil jsonUtil) {
        this.wiseSayingRepository = wiseSayingRepository;
        this.fileUtil = fileUtil;
        this.jsonUtil = jsonUtil;
    }

    /*
    *  public ArrayList<String[]> parseJsonToObjList(String jsonData) {

        ArrayList<String[]> objList = new ArrayList<>();

        StringTokenizer st = new StringTokenizer("{,}");
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            String item = parseJsonItem(token);
            String value = parseJsonValue(token);

            String[] set = new String[]{item, value};
            objList.add(set);
        }

        return objList;
    }
    *
    * */

    public int storedWishSaying(RequestRegisterWiseSaying request) {

        int lastWiseSayingIdx = wiseSayingRepository.findLastWiseSayingIdx();
        WiseSaying saved = new WiseSaying(++lastWiseSayingIdx, request.content(), request.author());

        // List에 저장
        wiseSayingRepository.savedWiseSaying(saved);

        // 명언 idx에 해당하는 파일에 명언 정보 저장
        fileUtil.setFileName(lastWiseSayingIdx + ".json");
        fileUtil.writeStringInFile(makeJsonForSaved(request, lastWiseSayingIdx));

        // 마지막 명언 idx 저장 파일에 명언 idx 저장
        fileUtil.setFileName("lastId.txt");
        fileUtil.writeIntInFile(lastWiseSayingIdx);

        return lastWiseSayingIdx;
    }

    private String makeJsonForSaved(RequestRegisterWiseSaying request, int lastWiseSayingIdx) {

        return String.format("""
                {
                    %s,
                    %s,
                    %s
                }
                """,
                jsonUtil.createIntJson("id", lastWiseSayingIdx),
                jsonUtil.createStringJson("content", request.content()),
                jsonUtil.createStringJson("author", request.author())
        );
    }

    public String findWishSayings() {

        // list 내


        wishSayings.forEach(wishSaying -> System.out.println(
                wishSaying.getIdx() + " / "
                        + wishSaying.getAuthor() + " / "
                        + wishSaying.getContent()));
    }

    public boolean removeWiseSaying(int wiseSayingIdx) {

        if (wishSayings.removeIf(wishSaying -> wishSaying.getIdx() == parseIdx)) {

            // 명언 idx에 해당하는 파일 삭제
            fileUtil.setFileDir(parseIdx + ".json");
            fileUtil.deleteWishSaying();


        } else {
        }

    }

    public Optional<ResponseUpdateWiseSaying> checkedWiseSayingExist(int wiseSayingIdx) {

        int findIdx = -1;
        for (int idx = 0; idx < wishSayings.size(); idx++) {
            WishSaying wishSaying = wishSayings.get(idx);
            if (wishSaying.getIdx() == parseIdx) {
                findIdx = idx;
            }
        }
    }

    public void updateWiseSaying(RequestUpdateWiseSaying request) {


        if (findIdx < 0) {
            System.out.println(parseIdx + "번 명언은 존재하지 않습니다.");
            continue;
        }

        WishSaying update = wishSayings.get(findIdx);

        System.out.println("명언(기존) : " + update.getContent());
        System.out.print("명령 : ");
        String updateContent = bufferedReader.readLine();

        System.out.println("작가(기존) : " + update.getAuthor());
        System.out.print("명령 : ");
        String updateAuthor = bufferedReader.readLine();

        update.setContent(updateContent);
        update.setAuthor(updateAuthor);

        // 명언 idx에 해당하는 파일에 명언 정보 저장
        fileUtil.setFileDir(parseIdx + ".json");
        fileUtil.writeWishSaying(update);
    }

    public void updateAllWiseSayings() {

        // 명언 list에 존재하는 모든 명언을 json 파일로 합치기
        fileUtil.setFileDir("data.json");
        fileUtil.writeAllWishSayings(wishSayings);
    }


}
