# 기존에 a2 데이터베이스가 존재 한다면 삭제
DROP DATABASE IF EXISTS `a2`;

# 새 데이터베이스(`a2`) 생성
CREATE DATABASE IF NOT EXISTS `a2`;

# 새 데이터베이스(`a2`) 선택
USE `a2`;

# article 테이블 생성(id, regDate, title, body)
CREATE TABLE article
(
    id      INT,
    regDate DATETIME,
    title   VARCHAR(100),
    `body`  TEXT
);

# article 테이블 조회(*)
SELECT *
FROM article;

# article 테이블에 data insert (regDate = NOW(), title = '제목', body = '내용')
INSERT INTO article(regDate, title, body)
VALUES (NOW(), '제목', '내용');

# article 테이블에 data insert (regDate = NOW(), title = '제목', body = '내용')
INSERT INTO article(regDate, title, body)
VALUES (NOW(), '제목', '내용');

# article 테이블 조회(*)
SELECT *
FROM article;

## id가 NULL인 데이터 생성이 가능하네?

# id 데이터는 꼭 필수 이기 때문에 NULL을 허용하지 않게 바꾼다.(alter table, not null)
## 기존의 NULL값 때문에 실패가 뜬다.
ALTER TABLE article
    MODIFY id INT NOT NULL;

# 기존의 NULL값이 0으로 바뀐다.
UPDATE article
SET id = 0
WHERE id IS NULL;

# NULL을 허용하지 않게 바꾼다.(alter table, not null)
ALTER TABLE article
    MODIFY id INT NOT NULL;

# article 테이블 조회(*)
SELECT *
FROM article;

# 생각해 보니 모든 행(row)의 id 값은 유니크 해야한다.(ADD PRIMARY KEY(id))
## 오류가 난다. 왜냐하면 기존의 데이터 중에서 중복되는게 있기 때문에
ALTER TABLE article
    ADD PRIMARY KEY (id);

# id가 0인 것 중에서 1개를 id 1로 바꾼다.
UPDATE article
SET id = 1
WHERE id = 0
LIMIT 1;

# article 테이블 조회(*)
SELECT *
FROM article;

# id가 0인것을 id 2로 바꾼다.
UPDATE article
SET id = 2
WHERE id = 0;

# 생각해 보니 모든 행(row)의 id 값은 유니크 해야한다.(ADD PRIMARY KEY(id))
## 이제 적용이 잘 된다.
ALTER TABLE article
    ADD PRIMARY KEY (id);

# id 칼럼에 auto_increment 를 건다.
## auto_increment 를 걸기전에 해당 칼럼은 무조건 key 여야 한다.
ALTER TABLE article
    MODIFY COLUMN id INT AUTO_INCREMENT;

# article 테이블 구조확인(desc)
DESC article;

# 나머지 칼럼 모두에도 not null을 적용해주세요.
ALTER TABLE article
    MODIFY COLUMN regDate DATETIME NOT NULL;
ALTER TABLE article
    MODIFY COLUMN title VARCHAR(100) NOT NULL;
ALTER TABLE article
    MODIFY COLUMN `body` TEXT NOT NULL;

# id 칼럼에 UNSIGNED 속성을 추가하세요.
ALTER TABLE article
    MODIFY COLUMN id INT UNSIGNED AUTO_INCREMENT;
DESC article;