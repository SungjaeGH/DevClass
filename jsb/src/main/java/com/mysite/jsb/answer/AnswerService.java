package com.mysite.jsb.answer;

import com.mysite.jsb.question.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(Question question, String content) {

        Answer answer = new Answer();
        answer.setCreateDate(LocalDateTime.now());
        answer.setContent(content);
        answer.setQuestion(question);

        answerRepository.save(answer);
    }
}
