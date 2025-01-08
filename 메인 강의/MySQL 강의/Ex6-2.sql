# 홍보를 마케팅으로 변경
## 구조를 변경하기로 결정(사원 테이블에서, 이제는 부서를 이름이 아닌 번호로 기억)
ALTER TABLE emp
    ADD COLUMN deptId INT UNSIGNED NOT NULL;
DESC emp;

SELECT *
FROM emp;

UPDATE emp
SET deptId = 1
WHERE deptName = '홍보';

UPDATE emp
SET deptId = 2
WHERE deptName = '기획';

# 사장님께 드릴 인명록을 생성

# 사장님께서 부서번호가 아니라 부서명을 알고 싶어하신다.
## 그래서 dept 테이블 조회법을 알려드리고 혼이 났다.
SELECT *
FROM emp;

SELECT *
FROM dept;