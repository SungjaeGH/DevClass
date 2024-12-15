package com.mysite.jsb;

import com.mysite.jsb.answer.Answer;
import com.mysite.jsb.answer.AnswerRepository;
import com.mysite.jsb.question.Question;
import com.mysite.jsb.question.QuestionRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JsbApplicationTests {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Test
    @DisplayName("question 테이블에 데이터를 삽입한다.")
    void saveJpaTest() {

        // given
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가요?");
        q1.setContent("sbb에 대해서 알고 싶습니다.");
        q1.setCreateDate(LocalDateTime.now());

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문입니다.");
        q2.setContent("id는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());

        // when
        this.questionRepository.save(q1);
        this.questionRepository.save(q2);

        // then
    }

    @Test
    @DisplayName("question 테이블에 존재하는 데이터를 조회한다.")
    void findAllJpaTest() {

        // given

        // when
        List<Question> all = this.questionRepository.findAll();
        Question q = all.get(0);

        // then
        assertEquals(4, all.size());
        assertEquals("sbb가 무엇인가요?", q.getSubject());
    }

    @Test
    @DisplayName("question 테이블에서 특정 id의 데이터를 가져온다.")
    void findByIdJpaTest() {

        // given

        // when
        Optional<Question> optionalQuestion = questionRepository.findById(1);

        // then
        if (optionalQuestion.isPresent()) {
            Question q = optionalQuestion.get();
            assertEquals("sbb가 무엇인가요?", q.getSubject());
        }
    }

    @Test
    @DisplayName("question 테이블에서 특정 제목의 데이터를 가져온다.")
    void findBySubjectJpaTest() {

        // given
        String subject = "sbb가 무엇인가요?";

        // when
        List<Question> questionList = questionRepository.findBySubject(subject);

        // then
        Question q = questionList.get(0);
        assertEquals(1, q.getId());
    }

    @Test
    @DisplayName("question 테이블에서 특정 제목과 내용의 데이터를 가져온다.")
    void findBySubjectAndContentJpaTest() {

        // given
        String subject = "sbb가 무엇인가요?";
        String content = "sbb에 대해서 알고 싶습니다.";

        // when
        List<Question> questionList = questionRepository.findBySubjectAndContent(subject, content);

        // then
        Question q = questionList.get(0);
        assertEquals(1, q.getId());
    }

    @Test
    @DisplayName("question 테이블에서 특정 문자열이 포함된 제목의 데이터를 가져온다.")
    void findBySubjectLikeJpaTest() {

        // given
        String subject = "sbb%";

        // when
        List<Question> questionList = questionRepository.findBySubjectLike(subject);

        // then
        Question q = questionList.get(0);
        assertEquals(1, q.getId());
    }

    @Test
    @DisplayName("question 테이블에서 특정 데이터를 수정한다.")
    void updateJpaTest() {

        // given
        int questionId = 1;

        // when
        Optional<Question> oq = questionRepository.findById(questionId);

        // then
        assertTrue(oq.isPresent());
        Question q = oq.get();
        q.setSubject("수정된 제목");
        questionRepository.save(q);
    }

    @Test
    @DisplayName("question 테이블에서 특정 데이터를 삭제한다.")
    void deleteJpaTest() {

        // given
        int questionId = 1;

        // when
        Optional<Question> oq = questionRepository.findById(questionId);

        // then
        assertEquals(4, questionRepository.count());
        assertTrue(oq.isPresent());

        // given
        Question q = oq.get();

        // when
        questionRepository.delete(q);

        // then
        assertEquals(3, questionRepository.count());
    }

    @Test
    @DisplayName("answer 테이블에 데이터 저장하기")
    void saveAnswerJpaTest() {

        // given
        int questionIdx = 2;

        // when
        Optional<Question> oq = questionRepository.findById(questionIdx);

        // then
        assertTrue(oq.isPresent());

        // given
        Question q = oq.get();
        Answer a = new Answer();
        a.setContent("네 자동으로 생성됩니다.");
        a.setQuestion(q);   // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
        a.setCreateDate(LocalDateTime.now());

        // when
        answerRepository.save(a);

        // then
    }

    @Test
    @DisplayName("answer 테이블 내 데이터를 조회한다.")
    void findAnswerByIdJpaTest() {

        // given
        int answerId = 1;

        // when
        Optional<Answer> oa = answerRepository.findById(answerId);

        // then
        assertTrue(oa.isPresent());
        assertEquals(2, oa.get().getQuestion().getId());
    }

    @Test
    @DisplayName("question 데이터에서 answer 데이터 찾기")
    @Transactional
    void findAnswerInQuestionJpaTest() {

        // given
        int questionId = 2;

        // when
        Optional<Question> oq = questionRepository.findById(questionId);

        // then
        assertTrue(oq.isPresent());

        // given
        Question q = oq.get();

        // when
        List<Answer> answerList = q.getAnswerList();

        // then
        assertEquals(1, answerList.size());
        assertEquals("네 자동으로 생성됩니다.", answerList.get(0).getContent());
    }
}