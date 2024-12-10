# 전 사원에 대하여, [부서명, 사원번호, 사원명] 양식으로 출력(IT 부서는 안나옴)
SELECT D.name AS `부서명`,
       E.id AS '사원번호',
       E.name AS '사원명'
FROM emp E INNER JOIN dept D on E.deptId = D.id;

# 전 사원에 대하여, [부서명, 사원번호, 사원명] 양식으로 출력(IT 부서가 아직 사원이 없더라도, 1줄이라도 나오도록 해주세요, LEFT JOIN 필요)
## IT부서는 [IT, NULL, NULL] 으로 출력
SELECT D.name AS `부서명`,
       E.id AS '사원번호',
       E.name AS '사원명'
FROM emp E RIGHT JOIN dept D on E.deptId = D.id;

# 전 사원에 대하여, [부서명, 사원번호, 사원명] 양식으로 출력
## IT부서는 [IT, 0, -] 으로 출력
SELECT D.name AS `부서명`,
       IFNULL(E.id, 0) AS '사원번호',
       IFNULL(E.name, '-') AS '사원명'
FROM emp E RIGHT JOIN dept D on E.deptId = D.id;

