CREATE TABLE employees
(
    employeeId number(10) NOT NULL PRIMARY KEY,
    firstName varchar(30)  NOT NULL,
    lastName  varchar(40) NOT NULL,
    title varchar(50) NOT NULL,
    managerId number(10) NULL,
    FOREIGN KEY (managerId)
        REFERENCES employees (employeeId)
);

INSERT INTO employees (employeeId, firstName, lastName, title, managerId) VALUES (1, 'Ken', 'Sánchez', 'Chief Executive Officer',NULL);
INSERT INTO employees (employeeId, firstName, lastName, title, managerId) VALUES (273, 'Bria', 'Welcker', 'Vice President of Sales',1);
INSERT INTO employees (employeeId, firstName, lastName, title, managerId) VALUES (274, 'Stephe', 'Jiang', 'North American Sales Manager',273);
INSERT INTO employees (employeeId, firstName, lastName, title, managerId) VALUES (275, 'Michael', 'Blythe', 'Sales Representative',274);
INSERT INTO employees (employeeId, firstName, lastName, title, managerId) VALUES (276, 'Linda', 'Mitchell', 'Sales Representative',274);
INSERT INTO employees (employeeId, firstName, lastName, title, managerId) VALUES (285, 'Syed', 'Abbas', 'Pacific Sales Manager',273);
INSERT INTO employees (employeeId, firstName, lastName, title, managerId) VALUES (286, 'Lyn', 'Tsoflias', 'Sales Representative',285);
INSERT INTO employees (employeeId, firstName, lastName, title, managerId) VALUES (16,  'David','Bradley', 'Marketing Manager',273);
INSERT INTO employees (employeeId, firstName, lastName, title, managerId) VALUES (23,  'Mary', 'Gibso', 'Marketing Specialist',16);
COMMIT;
