package com.ll.basic.domain.home.home.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private int age = 22;
    private final List<Integer> ages;

    @GetMapping("/")
    @ResponseBody
    public String showMain() {
        return "안녕하세요";
    }

    @GetMapping("/about")
    @ResponseBody
    public String showAbout() {
        return "저는 정성재입니다.";
    }

    @GetMapping("/age-up")
    @ResponseBody
    public int getAgeAndUp() {
        return ++this.age;
    }

    @GetMapping("/boolean")
    @ResponseBody
    public boolean getBoolean() {
        return true;
    }

    @GetMapping("/byte")
    @ResponseBody
    public byte getByte() {
        return 127;
    }

    @GetMapping("/short")
    @ResponseBody
    public short getShort() {
        return 32000;
    }

    @GetMapping("/long")
    @ResponseBody
    public long getLong() {
        return 100_000_000_000_000L;
    }

    @GetMapping("/char")
    @ResponseBody
    public char getChar() {
        return '뷁';
    }

    @GetMapping("/float")
    @ResponseBody
    public float getFloat() {
        return 3.14f;
    }

    @GetMapping("/double")
    @ResponseBody
    public double getDouble() {
        return 3.141592;
    }

    @GetMapping("/array")
    @ResponseBody
    public String[] getArray() {
        return new String[]{"a", "b", "c"};
    }

    @GetMapping("/list")
    @ResponseBody
    public List<String> getList() {
        return List.of("a", "b", "c");
    }

    @GetMapping("/map")
    @ResponseBody
    public Map<String, String> getMap() {
        return Map.of("name", "Paul", "age", "1");
    }

    @GetMapping("/article")
    @ResponseBody
    public Article getArticle() {
        return Article.builder()
                .content("내용")
                .title("제목")
                .build();
    }

    @GetMapping("/article-list")
    @ResponseBody
    public List<Article> getArticleList() {
        return List.of(
                Article.builder().content("내용1").title("제목1").build(),
                Article.builder().content("내용2").title("제목2").build()
        );
    }

    @GetMapping("/article-map")
    @ResponseBody
    public Map<String, Article> getArticleMap() {
        return Map.of(
                "article1", Article.builder().content("내용1").title("제목1").build(),
                "article2", Article.builder().content("내용2").title("제목2").build()
        );
    }

    @GetMapping("/article-list.html")
    @ResponseBody
    public String getArticlesDotHtml() {

        Article article1 = Article.builder().id(1).title("제목1").content("내용1").build();
        Article article2 = Article.builder().id(2).title("제목2").content("내용2").build();

        /* 첫번째 방법 : article이 생성될 때마다 계속 추가해야함
        return """
                <ul>
                    <li>
                        %d번 / %s
                    </li>
                    <li>
                        %d번 / %s
                    </li>
                </ul>
                """.formatted(
                        article1.getId(),
                        article1.getTitle(),
                        article2.getId(),
                        article2.getTitle());
         */

        List<Article> articles = List.of(article1, article2);

        String collected = articles.stream()
                .map(article -> "<li>%d번 / %s</li>".formatted(article.getId(), article.getTitle()))
                .collect(Collectors.joining("\n"));

        return "<ul>\n" + collected + "\n</ul>";
    }

    @GetMapping("/ages")
    @ResponseBody
    public List<Integer> getAges() {
        return this.ages;
    }

}

@Builder
@Getter
class Article {
    @Builder.Default
    private long id = 1L;
    private final String title;
    private final String content;
    @Builder.Default
    private boolean published = true; // getter 사용 시, isPublished 메서드로 해당 값을 가져올 수 있다.
}
