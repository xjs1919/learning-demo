CREATE TABLE employee
(
    employee_id INTEGER,
    employee_name VARCHAR2(20) NOT NULL,
    hire_date DATE,
    salary INTEGER,
    department_id INTEGER NOT NULL
);
INSERT INTO employee VALUES (9999, '王达梦','2008-05-30 00:00:00', 30000, 666);

CREATE TABLE department
(
    department_id INTEGER PRIMARY KEY,
    department_name VARCHAR(30) NOT NULL
);
INSERT INTO department VALUES(666, '数据库产品中心');