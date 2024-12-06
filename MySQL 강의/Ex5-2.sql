# 작성자(writer) 칼럼을 title 칼럼 다음에 추가해주세요.
ALTER TABLE article
    ADD COLUMN writer TEXT NOT NULL AFTER title;
DESC article;

# 작성자(writer) 칼럼의 이름을 nickname 으로 변경해주세요.(ALTER TABLE article CHANGE oldName newName TYPE 조건)
ALTER TABLE article
    CHANGE writer nickname TEXT NOT NULL;
DESC article;

# nickname 칼럼의 위치를 body 밑으로 보내주세요.(MODIFY COLUMN nickname)
ALTER TABLE article
    MODIFY COLUMN nickname TEXT NOT NULL AFTER `body`;
DESC article;

# hit 조회수 칼럼 추가 한 후 삭제해주세요.
ALTER TABLE article
    ADD COLUMN hit INT UNSIGNED NOT NULL;
ALTER TABLE article
    DROP COLUMN hit;
DESC article;

# hit 조회수 칼럼을 다시 추가
ALTER TABLE article
    ADD COLUMN hit INT UNSIGNED NOT NULL;
DESC article;

# 기존의 비어있는 닉네임 채워넣기(무명)
UPDATE article
SET nickname = '무명'
WHERE nickname = '';

# article 테이블에 데이터 추가(regDate = NOW(), title = '제목3', body = '내용3', nickname = '홍길순', hit = 10)
INSERT INTO article(regDate, title, body, nickname, hit)
VALUES (NOW(), '제목3', '내용3', '홍길순', 10);

# article 테이블에 데이터 추가(regDate = NOW(), title = '제목4', body = '내용4', nickname = '홍길동', hit = 55)
INSERT INTO article(regDate, title, body, nickname, hit)
VALUES (NOW(), '제목4', '내용4', '홍길동', 55);

# article 테이블에 데이터 추가(regDate = NOW(), title = '제목5', body = '내용5', nickname = '홍길동', hit = 10)
INSERT INTO article(regDate, title, body, nickname, hit)
VALUES (NOW(), '제목5', '내용5', '홍길동', 10);

# article 테이블에 데이터 추가(regDate = NOW(), title = '제목6', body = '내용6', nickname = '임꺽정', hit = 100)
INSERT INTO article(regDate, title, body, nickname, hit)
VALUES (NOW(), '제목6', '내용6', '임꺽정', 100);

# 조회수 가장 많은 게시물 3개 만 보여주세요., 힌트 : ORDER BY, LIMIT
SELECT *
FROM article
ORDER BY hit DESC
LIMIT 3;

# 작성자명이 '홍길'로 시작하는 게시물만 보여주세요., 힌트 : LIKE '홍길%'
SELECT *
FROM article
WHERE nickname LIKE ('홍길%');

# 조회수가 10 이상 55 이하 인것만 보여주세요., 힌트 : WHERE 조건1 AND 조건2
SELECT *
FROM article
WHERE hit BETWEEN 10 AND 55;

# 작성자가 '무명'이 아니고 조회수가 50 이하인 것만 보여주세요., 힌트 : !=
SELECT *
FROM article
WHERE nickname != '무명'
  AND hit <= 50;

# 작성자가 '무명' 이거나 조회수가 55 이상인 게시물을 보여주세요. 힌트 : OR
SELECT *
FROM article
WHERE nickname != '무명'
   OR hit >= 55;