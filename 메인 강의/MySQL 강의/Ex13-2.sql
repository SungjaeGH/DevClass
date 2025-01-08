# 유니크 인덱스를 loginID 칼럼에 걸기
## 설명 : mysql이 loginId의 고속검색을 위한 부가데이터를 자동으로 관리(생성/수정/삭제) 한다.
## 설명 : 이게 있고 없고가, 특정 상황에서 어마어마한 성능차이를 가져온다.
## 설명 : 생성된 인덱스의 이름은 기본적으로 칼럼명과 같다.
CREATE UNIQUE INDEX `member_index_id` ON `member` (`loginId`);

## 인덱스 확인
SHOW index FROM `member`;

# 검색속도 확인, loginId 가 'user1' 인 회원 검색 (1 in 115 ms)
SELECT *
FROM member
WHERE loginId = 'user1';

# 인덱스 삭제, `loginId` 이라는 이름의 인덱스 삭제
DROP INDEX `member_index_id` ON `member`;

## 인덱스 확인
SHOW INDEX FROM `member`;

# 회원 테이블 삭제
DROP TABLE `member`;

# 회원 테이블을 생성하는데, loginId에 uniqueIndex 까지 걸어주세요.
CREATE TABLE `member`
(
    id      INT UNSIGNED NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    UNIQUE INDEX `member_index_id` (id),
    regDate DATETIME     NOT NULL,
    loginId CHAR(50)     NOT NULL,
    loginPw VARCHAR(100) NOT NULL,
    `name`  CHAR(100)    NOT NULL
);

## 인덱스 확인
SHOW INDEX FROM `member`;

# 회원 2명 생성
## 조건 : (loginId = 'user1', loginPw = 'user1', `name` = '홍길동')
## 조건 : (loginId = 'user2', loginPw = 'user2', `name` = '홍길순')
INSERT INTO member(regDate, loginId, loginPw, name)
VALUES (NOW(), 'user1', 'user1', '홍길동');

INSERT INTO member(regDate, loginId, loginPw, name)
VALUES (NOW(), 'user2', 'user2', '홍길순');

# 회원 2배 증가 쿼리만들고 회원이 1만명 넘을 때 까지 반복 실행
## 힌트1 : INSERT INTO `tableName` (col1, col2, col3, col4)
## 힌트2 : SELECT NOW(), UUID(), 'pw', '아무개'
CREATE PROCEDURE LoopInsert2()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 10000
        DO
            INSERT INTO member(regDate, loginId, loginPw, name)
            VALUES (NOW(), UUID(), 'pw', '아무개');
            SET i = i + 1;
        end while;
    commit;
end;

CALL LoopInsert2();

# 회원수 확인
SELECT COUNT(*)
FROM `member`;

# 인덱스 쓰는지 확인
## 힌트 : EXPLAIN SELECT * ~
EXPLAIN
SELECT *
FROM `member`
WHERE loginId = 'user1';

# 인덱스 삭제, `loginId` 이라는 이름의 인덱스 삭제
ALTER TABLE `member`
    DROP INDEX `member_index_id`;

## 인덱스 확인
SHOW INDEX FROM `member`;

# 인덱스 안쓰는지 확인
## 힌트 : EXPLAIN SELECT * ~
EXPLAIN
SELECT *
FROM `member`
WHERE loginId = 'user1';