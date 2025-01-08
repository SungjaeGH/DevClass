# DB a3 삭제, 생성, 선택
DROP DATABASE IF EXISTS a3;
CREATE DATABASE a3;
USE a3;

# article 테이블 생성
CREATE TABLE article
(
    id        INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate   DATETIME     NOT NULL,
    `subject` VARCHAR(100) NOT NULL,
    `body`    TEXT         NOT NULL
);

# article 테이블에 데이터 3개 넣기
INSERT INTO article(regDate, subject, body)
VALUES (NOW(), '제목1', '내용1');

INSERT INTO article(regDate, subject, body)
VALUES (NOW(), '제목2', '내용2');

INSERT INTO article(regDate, subject, body)
VALUES (NOW(), '제목3', '내용4');

# article 확인
SELECT *
from article;

# comment 테이블 생성
CREATE TABLE `comment`
(
    id        INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate   DATETIME     NOT NULL,
    articleId INT UNSIGNED NOT NULL,
    `body`    TEXT         NOT NULL
);

# comment 테이블에 데이터 3개 넣기
## 1번글에 댓글 2개
## 2번글에 댓글 1개
## 3번글에 댓글 0개
INSERT INTO comment(regDate, articleId, body)
VALUES (NOW(), 1, '댓글 내용 1');

INSERT INTO comment(regDate, articleId, body)
VALUES (NOW(), 1, '댓글 내용 2');

INSERT INTO comment(regDate, articleId, body)
VALUES (NOW(), 2, '댓글 내용 3');

# comment 확인
SELECT *
FROM comment;

# 요구사항 1 : 댓글 리스트(게시물 번호, 댓글 번호, 댓글 내용)
SELECT articleId AS '게시물 번호', id AS '댓글 번호', body AS '댓글 내용'
FROM comment;

# 요구사항 2 : 댓글 리스트(JOIN 을 하되 ON 사용금지)(게시물 번호, 게시물 제목, 댓글 번호, 댓글 내용)
## 쓸데없는 조합이 생겨버렸다.
SELECT A.id AS '게시물 번호', A.subject AS '게시물 제목', C.id AS '댓글 번호', C.body AS '댓글 내용'
FROM comment C
         INNER JOIN article A;

# 요구사항 3 : 댓글 리스트(JOIN 을 하고 ON 도 사용)(게시물 번호, 게시물 제목, 댓글 번호, 댓글 내용)
SELECT A.id AS '게시물 번호', A.subject AS '게시물 제목', C.id AS '댓글 번호', C.body AS '댓글 내용'
FROM comment C
         INNER JOIN article A ON C.articleId = A.id;