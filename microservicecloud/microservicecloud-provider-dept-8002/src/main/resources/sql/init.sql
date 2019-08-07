DROP DATABASE IF EXISTS cloud02;
CREATE DATABASE cloud02 CHARACTER SET UTF8;
USE cloud02;

CREATE TABLE dept
(
	deptno BIGINT NOT NULL PRIMARY KEY auto_increment,
	dname VARCHAR(60),
	db_source varchar(60)
);

INSERT INTO dept(dname,db_source) VALUES ('开发部', DATABASE());
INSERT INTO dept(dname,db_source) VALUES ('人事部', DATABASE());

SELECT*FROM dept;
