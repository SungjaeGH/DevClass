## V3(V2에서 평균연봉이 5000이상인 부서로 추리기)

SELECT D.name                                           AS '부서명',
       GROUP_CONCAT(DISTINCT E.name ORDER BY E.id DESC) AS '사원리스트',
       TRUNCATE(AVG(E.salary), 0)            AS '평균연봉',
       MAX(salary)                                      AS '최고연봉',
       MIN(salary)                                      AS '최소연봉',
       COUNT(E.id)                                      AS '사원수'
FROM emp E
         INNER JOIN dept D ON E.deptId = D.id
GROUP BY D.id
HAVING `평균연봉` >= 5000;