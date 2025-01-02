package com.mysite.jsb.answer;

import com.mysite.jsb.question.DataNotFoundException;
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

    public Answer getAnswer(Integer id) {

        return answerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("answer not found"));
    }

    public void modify(Answer answer, String content) {

        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());

        answerRepository.save(answer);
    }

    public void delete(Answer answer) {

        answerRepository.delete(answer);
    }

    public void vote(Answer answer, SiteUser siteUser) {

        answer.getVoter().add(siteUser);
        answerRepository.save(answer);
    }
}
