# 하나의 쿼리로 최고연봉자와 최저연봉자의 이름과 연봉
(SELECT E.salary AS '연봉',
       E.name   AS `사원명`,
       '최고연봉자'  AS `타입`
FROM emp E
ORDER BY E.salary DESC
LIMIT 1)
UNION
(SELECT E.salary AS '연봉',
       E.name   AS `사원명`,
       '최저연봉자'  AS `타입`
FROM emp E
ORDER BY E.salary ASC
LIMIT 1)
ORDER BY `타입` ASC;