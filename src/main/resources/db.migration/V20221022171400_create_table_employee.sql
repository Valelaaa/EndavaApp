CREATE TABLE employee
(
    employee_id   VARCHAR2(36),
    first_name    VARCHAR2(16)        NOT NULL,
    last_name     VARCHAR2(16)        NOT NULL,
    department_id VARCHAR2(36),
    email         VARCHAR2(40)        NOT NULL UNIQUE,
    phone_number  VARCHAR2(16) UNIQUE NOT NULL,
    salary        NUMBER(6, 2),
    PRIMARY KEY (employee_id),
    CHECK ( salary > 1.0),
    FOREIGN KEY (department_id) REFERENCES department (department_id)
);
