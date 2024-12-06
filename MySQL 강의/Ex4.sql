# 날짜 칼럼 추가(id 칼럼 뒤에) => regDate DATETIME
ALTER TABLE article
    ADD COLUMN regData DATETIME AFTER id;

# 테이블 구조 확인
DESC article;

# 데이터 조회(*, 날짜 정보가 비어있음)
SELECT *
FROM article;

# 1번 게시물의 비어있는 날짜정보 채움(regDate = 2018-08-10 15:00:00)
UPDATE article
SET regData = '2018-08-10 15:00:00'
WHERE id = 1;

# 데이터 조회(*, 이제 2번 게시물의 날짜 정보만 넣으면 됩니다.)
SELECT *
FROM article;

# NOW() 함수 실행해보기 : SELECT NOW();
SELECT NOW();
SELECT SYSDATE();
SELECT CURDATE();

# 3번 게시물의 비어있는 날짜정보 채움(NOW())
UPDATE article
SET regData = NOW()
WHERE id = 3;

# 데이터 조회(*)
SELECT *
FROM article;