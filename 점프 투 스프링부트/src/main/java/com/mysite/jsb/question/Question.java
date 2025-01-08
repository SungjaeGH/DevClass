package com.mysite.jsb.question;

import com.mysite.jsb.answer.Answer;
import com.mysite.jsb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Question {

    /* [strategy 옵션 생략 시]
     * @GeneratedValue가 지정된 모든 속성에 번호를 차례대로 생성하므로 고유 번호를 가질 수 없음.
     * */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime modifyDate;

    @ManyToOne
    private SiteUser author;

    /*[CascadeType.REMOVE]
    * 질문을 삭제하면 그에 달린 답변들도 삭제 (연쇄 삭제 하기 위해)*/
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToMany
    private Set<SiteUser> voter;
}
