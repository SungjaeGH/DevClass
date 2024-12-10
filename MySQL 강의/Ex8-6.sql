## V2(조인해서 부서명까지 나오는 버전)

SELECT D.name                                           AS '부서명',
       GROUP_CONCAT(DISTINCT E.name ORDER BY E.id DESC) AS '사원리스트',
       CONCAT(FORMAT(AVG(salary), 0), '만원')            AS '평균연봉',
       MAX(salary)                                      AS '최고연봉',
       MIN(salary)                                      AS '최소연봉',
       COUNT(E.id)                                      AS '사원수'
FROM emp E
         INNER JOIN dept D ON E.deptId = D.id
GROUP BY D.id;