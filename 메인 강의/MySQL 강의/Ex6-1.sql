# a5 데이터베이스 삭제/생성/선택
DROP DATABASE IF EXISTS a5;
CREATE DATABASE a5;
USE a5;

# 부서(dept) 테이블 생성 및 홍보부서 기획부서 추가
CREATE TABLE dept
(
    id      INT UNSIGNED NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    regDate DATETIME     NOT NULL,
    `name`  CHAR(100)    NOT NULL UNIQUE
);

INSERT INTO dept(regdate, name)
VALUES (NOW(), '홍보');

INSERT INTO dept(regdate, name)
VALUES (NOW(), '기획');

# 사원(emp) 테이블 생성 및 홍길동사원(홍보부서), 홍길순사원(홍보부서), 임꺽정사원(기획부서) 추가
CREATE TABLE emp
(
    id       INT UNSIGNED NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    regDate  DATETIME     NOT NULL,
    `name`   CHAR(100)    NOT NULL,
    deptName CHAR(100)    NOT NULL
);

INSERT INTO emp(regDate, name, deptName)
VALUES (NOW(), '홍길동', '홍보');

INSERT INTO emp(regDate, name, deptName)
VALUES (NOW(), '홍길순', '홍보');

INSERT INTO emp(regDate, name, deptName)
VALUES (NOW(), '임꺽정', '기획');

# 홍보를 마케팅으로 변경
UPDATE emp
SET deptName = '마케팅'
WHERE deptName = '홍보';

# 마케팅을 홍보로 변경
UPDATE emp
SET deptName = '홍보'
WHERE deptName = '마케팅';