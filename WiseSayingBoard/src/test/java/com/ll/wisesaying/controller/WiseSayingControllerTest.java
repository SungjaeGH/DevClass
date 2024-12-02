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
        wiseSayingController.showWiseSayings();

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
}