CREATE TABLE department
(
    department_id   VARCHAR2(36),
    department_name VARCHAR2(16) NOT NULL,
    location_name   VARCHAR2(16) NOT NULL,
    PRIMARY KEY (department_id)
);
insert into department values('Dev','development','Chisinau');
select * from department;