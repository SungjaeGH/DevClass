# 부서별, 부서명, 사원리스트, 평균연봉, 최고연봉, 최소연봉, 사원수
## V1-1(조인 안한 버전(IF))

SELECT IF(deptId = 1, '홍보', '기획')          AS '부서명',
       GROUP_CONCAT(name ORDER BY id DESC) AS '사원리스트',
       AVG(salary)                         AS '평균연봉',
       MAX(salary)                         AS '최고연봉',
       MIN(salary)                         AS '최소연봉',
       COUNT(id)                           AS '사원수'
FROM emp
GROUP BY deptId;