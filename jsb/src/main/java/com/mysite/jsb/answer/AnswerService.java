package com.mysite.jsb.answer;

import com.mysite.jsb.question.Question;
import com.mysite.jsb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void create(Question question, String content, SiteUser author) {

        Answer answer = new Answer();
        answer.setCreateDate(LocalDateTime.now());
        answer.setContent(content);
        answer.setQuestion(question);
        answer.setAuthor(author);

        answerRepository.save(answer);
    }
}
