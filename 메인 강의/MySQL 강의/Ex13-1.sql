# 데이터베이스 a4가 존재하면 삭제
DROP DATABASE IF EXISTS `a4`;

# 데이터베이스 a4 생성
CREATE DATABASE `a4`;

# 데이터베이스 a4 선택
USE `a4`;

# 회원 테이블 생성, loginId, loginPw, `name`
## 조건 : loginId 칼럼에 UNIQUE INDEX 없이
CREATE TABLE `member`
(
    id      INT UNSIGNED NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    regDate DATETIME     NOT NULL,
    loginId CHAR(50)     NOT NULL,
    loginPw VARCHAR(100) NOT NULL,
    `name`  CHAR(100)    NOT NULL
);

# 회원 2명 생성
## 조건 : (loginId = 'user1', loginPw = 'user1', `name` = '홍길동')
## 조건 : (loginId = 'user2', loginPw = 'user2', `name` = '홍길순')
INSERT INTO member(regDate, loginId, loginPw, name)
VALUES (NOW(), 'user1', 'user1', '홍길동');

INSERT INTO member(regDate, loginId, loginPw, name)
VALUES (NOW(), 'user2', 'user2', '홍길순');

# 회원 2배 증가 쿼리만들고 회원이 백만명 넘을 때 까지 반복 실행
## 힌트1 : INSERT INTO `tableName` (col1, col2, col3, col4)
## 힌트2 : SELECT NOW(), UUID(), 'pw', '아무개'
# 프로시저 생성
CREATE PROCEDURE LoopInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 1000000
        DO
            INSERT INTO member(regDate, loginId, loginPw, name)
            VALUES (NOW(), UUID(), 'pw', '아무개');
            SET i = i + 1;
        end while;
    commit;
end;

# 프로시저 실행
CALL LoopInsert();

# 프로시저 삭제
DROP PROCEDURE LoopInsert;

# 회원수 확인
select count(*)
from member;

# 검색속도 확인 (1 s 798 ms)
SELECT *
FROM `member`
WHERE loginId = 'user1';