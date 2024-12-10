# 부서별, 부서명, 사원리스트, 평균연봉, 최고연봉, 최소연봉, 사원수
## V1-2(조인 안한 버전(CASE)) : 만약 사원명이 중복된다면 중복건 제거

SELECT CASE
           WHEN (deptId = 1) THEN '홍보'
           WHEN (deptId = 2) THEN '기획'
           ELSE '무소속'
           END                                      AS '부서명',
       GROUP_CONCAT(DISTINCT name ORDER BY id DESC) AS '사원리스트',
       AVG(salary)                                  AS '평균연봉',
       MAX(salary)                                  AS '최고연봉',
       MIN(salary)                                  AS '최소연봉',
       COUNT(id)                                    AS '사원수'
FROM emp
GROUP BY deptId;
