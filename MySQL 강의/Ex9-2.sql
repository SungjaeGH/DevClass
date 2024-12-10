# 1단계 : 각 부서별 최고연봉자의 연봉을 구한다.
## 힌트 : JOIN 필요없음
SELECT deptId,
       MAX(salary) AS maxSalary
FROM emp
GROUP BY deptId;

SELECT *
FROM emp;

# 2단계 : 전체 사원 중에서 자신이 속한 부서의 최고 연봉과 맞지 않는 사원들을 전부 필터링한다.
## 힌트 : JOIN 필요, 서브쿼리 필요
SELECT E2.name                            AS `사원명`,
       CONCAT(FORMAT(E2.salary, 0), '만원') AS `연봉`
FROM (SELECT deptId,
             MAX(salary) AS maxSalary
      FROM emp
      GROUP BY deptId) E1
         INNER JOIN emp E2 ON E1.deptId = E2.deptId AND E1.maxSalary = E2.salary;

# 3단계 : 입사일 추가, 부서명 추가(추가적으로 dept 테이블 join)
SELECT D.name                             AS `부서명`,
       E2.name                            AS `사원명`,
       D.regDate                          AS `입사일`,
       CONCAT(FORMAT(E2.salary, 0), '만원') AS `연봉`
FROM (SELECT deptId,
             MAX(salary) AS maxSalary
      FROM emp
      GROUP BY deptId) E1
         INNER JOIN emp E2 ON E1.deptId = E2.deptId AND E1.maxSalary = E2.salary
         INNER JOIN dept D ON E1.deptId = D.id;