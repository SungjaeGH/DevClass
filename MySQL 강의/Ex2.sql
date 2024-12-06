# 게시물 테이블 article(title, body)을 만듭니다.
# VARCHAR(100) => 문자 100개 저장가능
# text => 문자 많이 저장가능
CREATE TABLE article
(
    title  VARCHAR(100),
    `body` TEXT
);

# 잘 추가되었는지 확인, 리스팅과 구조까지 확인
SHOW TABLES;
DESC article;

# 데이터 하나 추가(title = 제목, body = 내용)
INSERT INTO article(title, body)
values ('제목', '내용');

# 데이터 조회(title 만)
SELECT title
FROM article;

# 데이터 조회(title, body)
SELECT title, `body`
FROM article;

# 데이터 조회(body, title)
SELECT `body`, title
FROM article;

# 데이터 조회(*)
SELECT *
FROM article;

# 데이터 또 하나 추가(title = 제목, body = 내용)
INSERT INTO article
SET title  = '제목',
    `body` = '내용';

# 데이터 조회(*, 어떤게 2번 게시물인지 알 수 없음)
SELECT *
FROM article;
