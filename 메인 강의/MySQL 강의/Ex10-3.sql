# 모든 부서별, 최고연봉, IT부서는 0원으로 표시
SELECT D.id,
       IF(MAX(E.salary) IS NULL, '0원', CONCAT(MAX(E.salary), '만원')) AS `최고연봉`
FROM dept D
         LEFT JOIN emp E ON D.id = E.deptId
GROUP BY D.id;

# 모든 부서별, 최저연봉, IT부서는 0원으로 표시
SELECT D.id,
       IF(MIN(E.salary) IS NULL, '0원', CONCAT(MIN(E.salary), '만원')) AS `최고연봉`
FROM dept D
         LEFT JOIN emp E ON D.id = E.deptId
GROUP BY D.id;


# 모든 부서별, 평균연봉, IT부서는 0원으로 표시
SELECT D.id,
       IF(AVG(E.salary) IS NULL, '0원', CONCAT(TRUNCATE(AVG(E.salary), 0), '만원')) AS `최고연봉`
FROM dept D
         LEFT JOIN emp E ON D.id = E.deptId
GROUP BY D.id;
