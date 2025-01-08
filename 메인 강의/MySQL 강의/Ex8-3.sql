# 사원 수 출력
SELECT COUNT(*)
FROM emp;

# 가장 큰 사원 번호 출력
SELECT id
FROM emp
ORDER BY id DESC
LIMIT 1;

# 가장 고액 연봉
SELECT MAX(salary) AS '고액 연봉'
FROM emp;

# 가장 저액 연봉
SELECT MIN(salary) AS '저액 연봉'
FROM emp;

# 회사에서 1년 고정 지출(인건비)
SELECT SUM(salary) * 12 AS '1년 인건비'
FROM emp;

# 부서별, 1년 고정 지출(인건비)
SELECT deptId, SUM(salary) * 12 AS '1년 인건비'
FROM emp
GROUP BY deptId;

# 부서별, 최고연봉
SELECT deptId, MAX(salary) AS '최고 연봉'
FROM emp
GROUP BY deptId;

# 부서별, 최저연봉
SELECT deptId, MIN(salary) AS '최저 연봉'
FROM emp
GROUP BY deptId;

# 부서별, 평균연봉
SELECT deptId, AVG(salary) AS '평균 연봉'
FROM emp
GROUP BY deptId;