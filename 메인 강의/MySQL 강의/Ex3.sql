# 테이블 구조 수정(id 칼럼 추가, first)
ALTER TABLE article
    ADD COLUMN id INT FIRST;

# 데이터 조회(*, id 칼럼의 값은 NULL)
SELECT *
FROM article;

# 기존 데이터에 id값 추가(id = 1, id IS NULL)
UPDATE article
SET id = 1
WHERE id IS NULL;

# 데이터 조회(*, 둘다 수정되어 버림..)
SELECT *
FROM article;

# 기존 데이터 중 1개만 id를 2로 변경(LIMIT 1)
UPDATE article
SET id = 2
LIMIT 1;

# 데이터 조회(*)
SELECT *
FROM article;

# 데이터 1개 추가(id = 3, title = 제목3, body = 내용3)
INSERT INTO article
VALUES (3, '제목3', '내용3');

# 데이터 조회(*)
SELECT *
FROM article;

# 2번 게시물, 데이터 삭제 => DELETE FROM {tableName} WHERE 조건;
DELETE
FROM article
WHERE id = 2;

# 데이터 조회(*)
SELECT *
FROM article;