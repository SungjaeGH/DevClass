# 사장님께 드릴 인명록을 생성(v2, 부서명 포함, ON 없이)
## 이상한 데이터가 생성되어서 혼남
SELECT emp.*, dept.name AS '부서명'
FROM emp
         INNER JOIN dept;

# 사장님께 드릴 인명록을 생성(v3, 부서명 포함, 올바른 조인 룰(ON) 적용)
## 보고용으로 좀 더 편하게 보여지도록 고쳐야 한다고 지적받음
SELECT emp.*,
       dept.id,
       dept.name AS `부서명`
FROM emp
         INNER JOIN dept ON dept.id = emp.id;

# 사장님께 드릴 인명록을 생성(v4, 사장님께서 보시기에 편한 칼럼명(AS))
SELECT emp.id            AS `사원번호`,
       emp.name          AS `사원명`,
       DATE(emp.regDate) AS `입사일`,
       dept.name         AS `부서명`
FROM emp
         INNER JOIN dept ON emp.deptId = dept.id
ORDER BY `부서명`, `사원명`;

# 사장님께 드릴 인명록을 생성(v5, 테이블 AS 적용)
SELECT E.id            AS `사원번호`,
       E.name          AS `사원명`,
       DATE(E.regDate) AS `입사일`,
       D.name          AS `부서명`
FROM emp AS E
         INNER JOIN dept AS D ON E.deptId = D.id
ORDER BY `부서명`, `사원명`;

# 추가내용
# 기획부서에 김영희가 배속되었다.
## 먼저 기획부서의 번호가 몇번인지 확인
SELECT id
FROM dept
WHERE name = '기획';

## 김영희 사원을 등록(기획)
INSERT INTO emp(regDate, name, deptName, deptId)
VALUES (NOW(), '김영희', '기획', 2);

# 신설 IT부서에 김철수가 배속되었다.
## IT 부서 생성
INSERT INTO dept(regDate, name) VALUES (NOW(), 'IT');

## IT 부서의 번호가 3번임을 확인
SELECT id
FROM dept
WHERE name = 'IT';

## 김철수 사원을 등록(IT)
INSERT INTO emp(regDate, name, deptName, deptId)
VALUES (NOW(), '김철수', 'IT', 3);