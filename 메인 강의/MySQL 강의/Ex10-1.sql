# a7 DB 삭제/생성/선택
DROP DATABASE IF EXISTS a7;
CREATE DATABASE a7;
USE a7;

# 부서(홍보, 기획, IT)
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
## IT부서는 아직 사원이 없음
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