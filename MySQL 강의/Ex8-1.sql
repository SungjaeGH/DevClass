DROP DATABASE IF EXISTS a6;
CREATE DATABASE a6;
USE a6;

CREATE TABLE board
(
    id      INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME     NOT NULL,
    `name`  CHAR(100)    NOT NULL UNIQUE
);

INSERT INTO board
SET regDate = NOW(),
    `name`  = '공지';

INSERT INTO board
SET regDate = NOW(),
    `name`  = '자유';

SELECT *
FROM board
WHERE `name` = '공지';

# 쿼리 1 : 테이블에서 바로 꺼내오는 경우
SELECT *
FROM board;

# 쿼리 2 : SELECT의 결과를 테이블 삼아서 거기에서 꺼내오는 경우
SELECT *
FROM (SELECT 1 AS id, NOW() AS regDate, '공지' AS `name`
      UNION
      SELECT 2 AS id, NOW() AS regDate, '자유' AS `name`) AS board;

# 쿼리 3 : SELECT의 결과를 테이블 삼아서 거기에서 꺼내오는 경우
SELECT *
FROM (SELECT *
      FROM board) AS board;

