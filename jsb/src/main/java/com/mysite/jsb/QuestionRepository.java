package com.mysite.jsb;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findBySubject(String subject);

    List<Question> findBySubjectAndContent(String subject, String Content);

    List<Question> findBySubjectLike(String subject);
}
