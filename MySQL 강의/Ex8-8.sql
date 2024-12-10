## V4(V3에서 HAVING 없이 서브쿼리로 수행)
### HINT, UNION을 이용한 서브쿼리
# SELECT *
# FROM (
#     SELECT 1 AS id
#     UNION
#     SELECT 2
#     UNION
#     SELECT 3
# ) AS A

SELECT *
FROM (SELECT D.name                     AS `부서명`,
             GROUP_CONCAT(E.`name`)     AS `사원리스트`,
             TRUNCATE(AVG(E.salary), 0) AS `평균연봉`, # TRUNCATE 는 소수점 이하 자리를 없애줍니다, 조금 더 전문적으로 꾸미려면 FORMAT 이 좋습니다.
             MAX(E.salary)              AS `최고연봉`,
             MIN(E.salary)              AS `최소연봉`,
             COUNT(*)                   AS `사원수`
      FROM emp E
               INNER JOIN dept D ON E.deptId = D.id
      GROUP BY E.deptId) AS G
WHERE G.`평균연봉` >= 5000;

