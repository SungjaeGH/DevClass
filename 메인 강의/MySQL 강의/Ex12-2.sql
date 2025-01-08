## 1단계 : dept 테이블과 CASE 문법으로 정답을 흉내내주세요.
SELECT D.name  AS `부서명`,
       CASE
           WHEN D.id = 1
               THEN 2
           WHEN D.id = 2
               THEN 1
           ELSE 0
           END AS `사원수`
FROM dept AS D
ORDER BY D.id ASC;

## 2단계 : emp 테이블을 통해서 각 부서별 부서번호와 사원수를 출력해주세요. IT 부서는 누락되어도 됩니다.
SELECT D.id     AS `부서번호`,
       COUNT(*) AS `사원수`
FROM emp E
         INNER JOIN dept D ON E.deptId = D.id
GROUP BY D.id;

## 3단계 : 2단계에서 부서번호를 부서명으로 변경해주세요. INNER JOIN 사용. IT 부서는 누락되어도 됩니다.
SELECT D.name   AS `부서명`,
       COUNT(*) AS `사원수`
FROM emp E
         INNER JOIN dept D ON E.deptId = D.id
GROUP BY D.id
ORDER BY D.id DESC;

## 4단계 : LEFT JOIN 을 사용하여, IT부서가 노출되도록 하고 GROUP BY 를 해제해주세요. 사원이 없으면 인원이 0명으로 나오게 해주세요.
SELECT D.name                     AS `부서명`,
       IF(E.id IS NOT NULL, 1, 0) AS `인원`
FROM dept AS D
         LEFT JOIN emp AS E
                   ON D.id = E.deptId;

## 5단계 : GROUP BY 와 SUM 을 통해서 각 부서별 부서명과 사원수를 출력해주세요.
## 정답 v1 : SUM 과 IF 를 사용한 버전
SELECT D.name                            AS `부서명`,
       SUM(IF((E.id) IS NOT NULL, 1, 0)) AS `인원`
FROM dept D
         LEFT JOIN emp E ON D.id = E.deptId
GROUP BY D.id;

## 6단계 : COUNT 를 통해서 각 부서별 부서명과 사원수를 출력해주세요.
## 정답 v2 : COUNT 를 사용한 버전
SELECT D.name      AS `부서명`,
       COUNT(E.id) AS `인원`
FROM dept D
         LEFT JOIN emp E ON D.id = E.deptId
GROUP BY D.id;