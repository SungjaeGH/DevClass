# 전체 데이터베이스 리스팅
SHOW DATABASES;

# `mysql` 데이터 베이스 선택
USE mysql;

# 테이블 리스팅
SHOW TABLES;

# 특정 테이블의 구조
DESC `user`;

# `test` 데이터 베이스 선택(없으면 먼저 만들어주세요.)
CREATE DATABASE IF NOT EXISTS test;
USE test;

# 테이블 리스팅
SHOW TABLES;

# 기존에 a1 데이터베이스가 존재 한다면 삭제
DROP DATABASE IF EXISTS `a1`;

# 새 데이터베이스(`a1`) 생성
CREATE DATABASE `a1`;

# 데이터베이스(`a1`) 선택
USE `a1`;

# 데이터베이스 추가 되었는지 확인
SHOW DATABASES;

# 테이블 확인
SHOW TABLES;