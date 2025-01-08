package com.ll.wisesaying.controller;

import com.ll.wisesaying.domain.entity.WiseSaying;
import org.junit.jupiter.api.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class WiseSayingControllerTest extends TestUtil {

    @BeforeEach
    void setUp() {
        // 객체 의존성 주입 및 디렉토리 생성
        init();
    }

    @AfterEach
    void tearDown() throws IOException {
        // 모든 파일, 디렉토리 삭제
        detach();
    }

    @Test
    @DisplayName("명언 등록")
    void registerWiseSaying() {

        // given
        ByteArrayOutputStream output = setOutToByteArray();

        try (BufferedReader bufferedReader = generateScanner(
                """
                        명언1
                        작가1
                        """.strip().trim()
        )) {

            // when
            wiseSayingController.registerWiseSaying(bufferedReader);

            // then
            List<WiseSaying> findList = wiseSayingRepository.findAllWiseSayings();
            assertEquals(1, findList.size());

            findList.stream().findFirst().ifPresentOrElse(findData -> {
                assertNotEquals(0, findData.getIdx());
                assertEquals("명언1", findData.getContent());
                assertEquals("작가1", findData.getAuthor());
            }, Assertions::fail);

            assertThat(output.toString().trim()).contains("1번 명언이 등록되었습니다.");

            clearSetOutToByteArray(output);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("명언 목록 조회")
    void showWiseSayings() {

        // given
        ByteArrayOutputStream output = setOutToByteArray();
        wiseSayingRepository.savedWiseSaying("명언1", "작가1", true);

        // when
        wiseSayingController.showWiseSayings("목록");

        // then
        String outputString = output.toString().trim();
        String[] outputParts = outputString.split("\n");

        assertThat(outputParts[0]).contains("번호 / 작가 / 명언");
        assertThat(outputParts[1]).contains("-------------------------");
        assertThat(outputParts[2]).contains("1 / 작가1 / 명언1");

        clearSetOutToByteArray(output);
    }

    @Test
    @DisplayName("명언이 삭제되었을 경우")
    void deleteWiseSaying() {

        // given
        ByteArrayOutputStream output = setOutToByteArray();
        wiseSayingRepository.savedWiseSaying("명언1", "작가1", true);
        wiseSayingRepository.savedWiseSaying("명언2", "작가2", true);

        // when
        wiseSayingController.deleteWiseSaying("삭제?id=1");

        // then
        assertThat(output.toString().trim()).contains("1번 명언이 삭제되었습니다.");

        clearSetOutToByteArray(output);
    }

    @Test
    @DisplayName("삭제할 명언이 존재하지 않을 경우")
    void noDeleteWiseSaying() {

        // given
        ByteArrayOutputStream output = setOutToByteArray();
        wiseSayingRepository.savedWiseSaying("명언1", "작가1", true);
        wiseSayingRepository.savedWiseSaying("명언2", "작가2", true);

        // when
        wiseSayingController.deleteWiseSaying("삭제?id=3");

        // then
        assertThat(output.toString().trim()).contains("3번 명언은 존재하지 않습니다.");

        clearSetOutToByteArray(output);
    }

    @Test
    @DisplayName("명언이 수정되었을 경우")
    void updateWiseSaying() {

        // given
        ByteArrayOutputStream output = setOutToByteArray();
        wiseSayingRepository.savedWiseSaying("명언1", "작가1", true);

        try (BufferedReader bufferedReader = generateScanner(
                """
                        명언1수정
                        작가1수정
                        """.strip().trim()
        )) {

            // when
            wiseSayingController.updateWiseSaying("수정?id=1", bufferedReader);

            // then
            String outputString = output.toString().trim();
            String[] outputParts = outputString.split("\n");

            assertThat(outputParts[0]).contains("명언(기존) : 명언1");
            assertThat(outputParts[1]).contains("작가(기존) : 작가1");

            List<WiseSaying> findList = wiseSayingRepository.findAllWiseSayings();
            assertEquals(1, findList.size());

            findList.stream().findFirst().ifPresentOrElse(findData -> {
                assertNotEquals(0, findData.getIdx());
                assertEquals("명언1수정", findData.getContent());
                assertEquals("작가1수정", findData.getAuthor());
            }, Assertions::fail);

        } catch (IOException e) {
            e.printStackTrace();
        }

        clearSetOutToByteArray(output);
    }

    @Test
    @DisplayName("수정할 명언이 존재하지 않을 경우")
    void noUpdateWiseSaying() {

        // given
        ByteArrayOutputStream output = setOutToByteArray();
        wiseSayingRepository.savedWiseSaying("명언1", "작가1", true);
        wiseSayingRepository.savedWiseSaying("명언2", "작가2", true);

        // when
        try (BufferedReader bufferedReader = generateScanner(
                """
                        명언3수정
                        작가3수정
                        """.strip().trim()
        )) {
            // when
            wiseSayingController.updateWiseSaying("수정?id=3", bufferedReader);

            // then
            assertThat(output.toString().trim()).contains("3번 명언은 존재하지 않습니다.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        clearSetOutToByteArray(output);
    }

    @Test
    @DisplayName("명언 빌드")
    void buildWiseSayings() {

        // given
        ByteArrayOutputStream output = setOutToByteArray();
        wiseSayingRepository.savedWiseSaying("명언1", "작가1", true);
        wiseSayingRepository.savedWiseSaying("명언2", "작가2", true);

        // when
        wiseSayingController.buildWiseSayings();

        // then

        assertThat(output.toString().trim()).contains("data.json 파일의 내용이 갱신되었습니다.");

        clearSetOutToByteArray(output);
    }

    @Test
    @DisplayName("명언 목록 조회 : 명언 type과 일치하는 검색 결과가 있을 경우")
    void matchContentKeywordInWiseSayings() {

        // given
        ByteArrayOutputStream output = setOutToByteArray();
        wiseSayingRepository.savedWiseSaying("명언1", "작가1", true);
        wiseSayingRepository.savedWiseSaying("명언2", "작가2", true);

        // when
        wiseSayingController.showWiseSayings("목록?keywordType=content&keyword=명언");

        // then
        List<WiseSaying> testResult = wiseSayingRepository.findAllWiseSayingsByKeyword("content", "명언");
        assertThat(testResult.size()).isEqualTo(2);
        WiseSaying first = testResult.get(0);
        assertThat(first.getContent()).contains("명언1");
        assertThat(first.getAuthor()).contains("작가1");

        WiseSaying second = testResult.get(1);
        assertThat(second.getContent()).contains("명언2");
        assertThat(second.getAuthor()).contains("작가2");

        String outputString = output.toString().trim();
        String[] outputParts = outputString.split("\n");

        assertThat(outputParts[0]).contains("-------------------------");
        assertThat(outputParts[1]).contains("검색타입 : content");
        assertThat(outputParts[2]).contains("검색어 : 명언");
        assertThat(outputParts[3]).contains("-------------------------");
        assertThat(outputParts[4]).contains("번호 / 작가 / 명언");
        assertThat(outputParts[5]).contains("-------------------------");

        clearSetOutToByteArray(output);
    }

    @Test
    @DisplayName("명언 목록 조회 : 작가 type과 일치하는 검색 결과가 있을 경우")
    void matchAuthorKeywordInWiseSayings() {

        // given
        ByteArrayOutputStream output = setOutToByteArray();
        wiseSayingRepository.savedWiseSaying("명언1", "작가1", true);
        wiseSayingRepository.savedWiseSaying("명언2", "작가2", true);

        // when
        wiseSayingController.showWiseSayings("목록?keywordType=author&keyword=작가");

        // then
        List<WiseSaying> testResult = wiseSayingRepository.findAllWiseSayingsByKeyword("author", "작가");
        assertThat(testResult.size()).isEqualTo(2);
        WiseSaying first = testResult.get(0);
        assertThat(first.getContent()).contains("명언1");
        assertThat(first.getAuthor()).contains("작가1");

        WiseSaying second = testResult.get(1);
        assertThat(second.getContent()).contains("명언2");
        assertThat(second.getAuthor()).contains("작가2");

        String outputString = output.toString().trim();
        String[] outputParts = outputString.split("\n");

        assertThat(outputParts[0]).contains("-------------------------");
        assertThat(outputParts[1]).contains("검색타입 : author");
        assertThat(outputParts[2]).contains("검색어 : 작가");
        assertThat(outputParts[3]).contains("-------------------------");
        assertThat(outputParts[4]).contains("번호 / 작가 / 명언");
        assertThat(outputParts[5]).contains("-------------------------");

        clearSetOutToByteArray(output);
    }

    @Test
    @DisplayName("명언 목록 조회 : 일치하는 검색 결과가 없을 경우")
    void noMatchKeywordInWiseSayings() {

        // given
        ByteArrayOutputStream output = setOutToByteArray();
        wiseSayingRepository.savedWiseSaying("명언1", "작가1", true);
        wiseSayingRepository.savedWiseSaying("명언2", "작가2", true);

        // when
        wiseSayingController.showWiseSayings("목록?keywordType=author&keyword=없음");

        // then
        List<WiseSaying> testResult = wiseSayingRepository.findAllWiseSayingsByKeyword("author", "없음");
        assertThat(testResult.size()).isEqualTo(0);

        String outputString = output.toString().trim();
        String[] outputParts = outputString.split("\n");

        assertThat(outputParts[0]).contains("-------------------------");
        assertThat(outputParts[1]).contains("검색타입 : author");
        assertThat(outputParts[2]).contains("검색어 : 없음");
        assertThat(outputParts[3]).contains("-------------------------");
        assertThat(outputParts[4]).contains("번호 / 작가 / 명언");
        assertThat(outputParts[3]).contains("-------------------------");

        clearSetOutToByteArray(output);
    }

    @Test
    @DisplayName("명언 목록 조회 : 1 페이지 목록 확인")
    void showWiseSayingsByPaging1() {

        // given
        ByteArrayOutputStream output = setOutToByteArray();
        for (int idx = 1; idx <= 10; idx++) {
            wiseSayingRepository.savedWiseSaying("명언" + idx, "작가" + idx, true);
        }

        // when
        wiseSayingController.showWiseSayings("목록?page=1");

        // then
        List<WiseSaying> findResult = wiseSayingRepository.findAllWiseSayings();
        List<WiseSaying> pagingResult = wiseSayingRepository.findWiseSayingsByPagingDesc(findResult, 1, 5);

        assertThat(pagingResult.size()).isEqualTo(5);

        String outputString = output.toString().trim();
        String[] outputParts = outputString.split("\n");

        assertThat(outputParts[0]).contains("번호 / 작가 / 명언");
        assertThat(outputParts[1]).contains("-------------------------");
        assertThat(outputParts[2]).contains("10 / 작가10 / 명언10");
        assertThat(outputParts[3]).contains("9 / 작가9 / 명언9");
        assertThat(outputParts[4]).contains("8 / 작가8 / 명언8");
        assertThat(outputParts[5]).contains("7 / 작가7 / 명언7");
        assertThat(outputParts[6]).contains("6 / 작가6 / 명언6");
        assertThat(outputParts[7]).contains("-------------------------");
        assertThat(outputParts[8]).contains("페이지 : [1] / 2");

        clearSetOutToByteArray(output);
    }

    @Test
    @DisplayName("명언 목록 조회 : 2 페이지 목록 확인")
    void showWiseSayingsByPaging2() {

        // given
        ByteArrayOutputStream output = setOutToByteArray();
        for (int idx = 1; idx <= 10; idx++) {
            wiseSayingRepository.savedWiseSaying("명언" + idx, "작가" + idx, true);
        }

        // when
        wiseSayingController.showWiseSayings("목록?page=2");

        // then
        List<WiseSaying> findResult = wiseSayingRepository.findAllWiseSayings();
        List<WiseSaying> pagingResult = wiseSayingRepository.findWiseSayingsByPagingDesc(findResult, 2, 5);

        assertThat(pagingResult.size()).isEqualTo(5);

        String outputString = output.toString().trim();
        String[] outputParts = outputString.split("\n");

        assertThat(outputParts[0]).contains("번호 / 작가 / 명언");
        assertThat(outputParts[1]).contains("-------------------------");
        assertThat(outputParts[2]).contains("5 / 작가5 / 명언5");
        assertThat(outputParts[3]).contains("4 / 작가4 / 명언4");
        assertThat(outputParts[4]).contains("3 / 작가3 / 명언3");
        assertThat(outputParts[5]).contains("2 / 작가2 / 명언2");
        assertThat(outputParts[6]).contains("1 / 작가1 / 명언1");
        assertThat(outputParts[7]).contains("-------------------------");
        assertThat(outputParts[8]).contains("페이지 : 1 / [2]");

        clearSetOutToByteArray(output);
    }

    @Test
    @DisplayName("명언 목록 조회 : 페이지 번호를 생략했을 경우")
    void showWiseSayingsBySkipPaging() {

        // given
        ByteArrayOutputStream output = setOutToByteArray();
        for (int idx = 1; idx <= 10; idx++) {
            wiseSayingRepository.savedWiseSaying("명언" + idx, "작가" + idx, true);
        }

        // when
        wiseSayingController.showWiseSayings("목록");

        // then
        List<WiseSaying> findResult = wiseSayingRepository.findAllWiseSayings();
        List<WiseSaying> pagingResult = wiseSayingRepository.findWiseSayingsByPagingDesc(findResult, 1, 5);

        assertThat(pagingResult.size()).isEqualTo(5);

        String outputString = output.toString().trim();
        String[] outputParts = outputString.split("\n");

        assertThat(outputParts[0]).contains("번호 / 작가 / 명언");
        assertThat(outputParts[1]).contains("-------------------------");
        assertThat(outputParts[2]).contains("10 / 작가10 / 명언10");
        assertThat(outputParts[3]).contains("9 / 작가9 / 명언9");
        assertThat(outputParts[4]).contains("8 / 작가8 / 명언8");
        assertThat(outputParts[5]).contains("7 / 작가7 / 명언7");
        assertThat(outputParts[6]).contains("6 / 작가6 / 명언6");
        assertThat(outputParts[7]).contains("-------------------------");
        assertThat(outputParts[8]).contains("페이지 : [1] / 2");

        clearSetOutToByteArray(output);
    }

    @Test
    @DisplayName("명언 목록 조회 : 검색어와 함께 조회했을 경우")
    void showWiseSayingsByPagingWithKeyword() {

        // given
        ByteArrayOutputStream output = setOutToByteArray();
        for (int idx = 1; idx <= 10; idx++) {
            wiseSayingRepository.savedWiseSaying("명언" + idx, "작가" + idx, true);
        }

        // when
        wiseSayingController.showWiseSayings("목록?keywordType=author&keyword=작가1&page=1");

        // then
        List<WiseSaying> findResult = wiseSayingRepository.findAllWiseSayingsByKeyword("author", "작가1");
        List<WiseSaying> pagingResult = wiseSayingRepository.findWiseSayingsByPagingDesc(findResult, 1, 5);

        assertThat(pagingResult.size()).isEqualTo(2);

        String outputString = output.toString().trim();
        String[] outputParts = outputString.split("\n");

        assertThat(outputParts[0]).contains("-------------------------");
        assertThat(outputParts[1]).contains("검색타입 : author");
        assertThat(outputParts[2]).contains("검색어 : 작가1");
        assertThat(outputParts[3]).contains("-------------------------");
        assertThat(outputParts[4]).contains("번호 / 작가 / 명언");
        assertThat(outputParts[5]).contains("-------------------------");
        assertThat(outputParts[6]).contains("10 / 작가10 / 명언10");
        assertThat(outputParts[7]).contains("1 / 작가1 / 명언1");
        assertThat(outputParts[8]).contains("-------------------------");
        assertThat(outputParts[9]).contains("페이지 : [1]");

        clearSetOutToByteArray(output);
    }

    @Test
    @DisplayName("명언 목록 조회 : 찾을 명언이 존재하지 않을 경우")
    void noShowWiseSayingsByPaging() {

        // given
        ByteArrayOutputStream output = setOutToByteArray();
        for (int idx = 1; idx <= 10; idx++) {
            wiseSayingRepository.savedWiseSaying("명언" + idx, "작가" + idx, true);
        }

        // when
        wiseSayingController.showWiseSayings("목록?keywordType=author&keyword=없음&page=2");

        // then
        List<WiseSaying> findResult = wiseSayingRepository.findAllWiseSayingsByKeyword("author", "없음");
        List<WiseSaying> pagingResult = wiseSayingRepository.findWiseSayingsByPagingDesc(findResult, 2, 5);

        assertThat(pagingResult.size()).isEqualTo(0);

        String outputString = output.toString().trim();
        String[] outputParts = outputString.split("\n");

        assertThat(outputParts[0]).contains("-------------------------");
        assertThat(outputParts[1]).contains("검색타입 : author");
        assertThat(outputParts[2]).contains("검색어 : 없음");
        assertThat(outputParts[3]).contains("-------------------------");
        assertThat(outputParts[4]).contains("번호 / 작가 / 명언");
        assertThat(outputParts[5]).contains("-------------------------");
        assertThat(outputParts[6]).contains("-------------------------");
        assertThat(outputParts[7]).contains("페이지 : [1]");

        clearSetOutToByteArray(output);
    }
}