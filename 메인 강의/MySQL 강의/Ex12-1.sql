# 현재 세션에서 `ONLY_FULL_GROUP_BY` 모드 끄기, 이 부분은 이해하지 않으셔도 됩니다.
## 영구적으로 설정되는 것은 아닙니다.
SET SESSION sql_mode = (SELECT REPLACE(@@sql_mode, 'ONLY_FULL_GROUP_BY', ''));
SET sql_mode = (SELECT REPLACE(@@sql_mode, 'ONLY_FULL_GROUP_BY', ''));

# a9 DB 삭제/생성/선택
DROP DATABASE IF EXISTS a9;
CREATE DATABASE a9;
USE a9;

# 부서(홍보, 기획)
CREATE TABLE dept
(
    id      INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME     NOT NULL,
    `name`  CHAR(100)    NOT NULL UNIQUE
);

INSERT INTO dept
SET regDate = NOW(),
    `name`  = '홍보';

INSERT INTO dept
SET regDate = NOW(),
    `name`  = '기획';

INSERT INTO dept
SET regDate = NOW(),
    `name`  = 'IT';

# 사원(홍길동/홍보/5000만원, 홍길순/홍보/6000만원, 임꺽정/기획/4000만원)
CREATE TABLE emp
(
    id      INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    regDate DATETIME     NOT NULL,
    `name`  CHAR(100)    NOT NULL,
    deptId  INT UNSIGNED NOT NULL,
    salary  INT UNSIGNED NOT NULL
);

INSERT INTO emp
SET regDate = NOW(),
    `name`  = '홍길동',
    deptId  = 1,
    salary  = 5000;

INSERT INTO emp
SET regDate = NOW(),
    `name`  = '홍길순',
    deptId  = 1,
    salary  = 6000;

INSERT INTO emp
SET regDate = NOW(),
    `name`  = '임꺽정',
    deptId  = 2,
    salary  = 4000;