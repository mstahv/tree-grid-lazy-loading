-- Table and test data derived from MS SQL Server sample
-- Create employees table.
CREATE TABLE employees
(
    employeeId serial PRIMARY KEY,
    firstName varchar(30)  NOT NULL,
    lastName  varchar(40) NOT NULL,
    title varchar(50) NOT NULL,
    managerId int NULL,
    FOREIGN KEY (managerId)
        REFERENCES employees (employeeId)
);
-- Test data
INSERT INTO employees VALUES
 (1, 'Ken', 'Sánchez', 'Chief Executive Officer',NULL)
,(273, 'Bria', 'Welcker', 'Vice President of Sales',1)
,(274, 'Stephe', 'Jiang', 'North American Sales Manager',273)
,(275, 'Michael', 'Blythe', 'Sales Representative',274)
,(276, 'Linda', 'Mitchell', 'Sales Representative',274)
,(285, 'Syed', 'Abbas', 'Pacific Sales Manager',273)
,(286, 'Lyn', 'Tsoflias', 'Sales Representative',285)
,(16,  'David','Bradley', 'Marketing Manager',273)
,(23,  'Mary', 'Gibso', 'Marketing Specialist',16);


-- Starting with John Doe as a manager
INSERT INTO employees VALUES
 (24, 'John', 'Doe', 'Manager',275)
,(25, 'Jane', 'Smith', 'Software Developer',24)
,(26, 'Robert', 'Johnson', 'Engineer',24)
,(27, 'Emily', 'Jones', 'Designer',24)
,(28, 'William', 'Brown', 'Software Developer',24)
,(29, 'Elizabeth', 'Taylor', 'Engineer',24)

,(30, 'Richard', 'Davis', 'Manager',275)
,(31, 'Jessica', 'Miller', 'Designer',30)
,(32, 'Joseph', 'Clark', 'Software Developer',30)
,(33, 'Susan', 'Martinez', 'Engineer',30)
,(34, 'Sarah', 'Moore', 'Designer',30)
,(35, 'James', 'Anderson', 'Software Developer',30)

,(36, 'Nancy', 'Rodriguez', 'Manager',273)
,(37, 'Paul', 'Garcia', 'Engineer',36)
,(38, 'David', 'Hernandez', 'Designer',36)
,(39, 'Linda', 'Hall', 'Software Developer',36)
,(40, 'Angela', 'Thompson', 'Engineer',36)
,(41, 'Michael', 'King', 'Designer',36)

,(42, 'Barbara', 'Lee', 'Manager',273)
,(43, 'Daniel', 'Turner', 'Software Developer',42)
,(44, 'Lisa', 'Walker', 'Engineer',42)
,(45, 'Mark', 'Harris', 'Designer',42)
,(46, 'Jennifer', 'Lewis', 'Software Developer',42)
,(47, 'Steven', 'Carter', 'Engineer',42)

,(48, 'Maria', 'Roberts', 'Manager',1)
,(49, 'Andrew', 'Phillips', 'Designer',48)
,(50, 'Sandra', 'Campbell', 'Software Developer',48)
,(51, 'Brian', 'Mitchell', 'Engineer',48)
,(52, 'Mary', 'Adams', 'Designer',48)
,(53, 'Patricia', 'Baker', 'Software Developer',48)
;
-- ... continue in this pattern

-- Test data continued by ChatGPT...
INSERT INTO employees VALUES
 (54, 'Ronald', 'Gonzalez', 'Software Developer',49)
,(55, 'Karen', 'Nelson', 'Engineer',47)
,(56, 'Kevin', 'Scott', 'Product Manager',50)
,(57, 'Dorothy', 'Evans', 'Designer',56)
,(58, 'Jason', 'Perez', 'IT Specialist',53)
,(59, 'Gary', 'Edwards', 'Software Developer',54)
,(60, 'Michelle', 'Collins', 'Engineer',55)
,(61, 'Justin', 'Morris', 'Designer',56)
,(62, 'Kathryn', 'Murphy', 'IT Specialist',58)
,(63, 'Melissa', 'Sanchez', 'Engineer',60)
,(64, 'Jeffrey', 'Stewart', 'Designer',57)
,(65, 'Laura', 'Bailey', 'Software Developer',59)
,(66, 'Joshua', 'Gutierrez', 'Product Manager',56)
,(67, 'Samantha', 'Peterson', 'Engineer',63)
,(68, 'Timothy', 'Long', 'Designer',66)
,(69, 'Rebecca', 'Barnes', 'IT Specialist',62)
,(70, 'Raymond', 'Fisher', 'Software Developer',65)
,(71, 'Pamela', 'Henderson', 'Engineer',67)
,(72, 'Stephen', 'Graham', 'Designer',68)
,(73, 'Anna', 'Wood', 'IT Specialist',69);

-- Continuing the test data insertion
INSERT INTO employees VALUES
 (74, 'Benjamin', 'Wright', 'Manager',273)
,(75, 'Christina', 'Robinson', 'Software Developer',74)
,(76, 'Gregory', 'Lopez', 'Engineer',74)
,(77, 'Amy', 'Russell', 'Designer',74)
,(78, 'Sharon', 'Harris', 'Software Developer',74)
,(79, 'Thomas', 'Parker', 'Engineer',74)

,(80, 'Walter', 'Torres', 'Manager',273)
,(81, 'Cynthia', 'Perry', 'Designer',80)
,(82, 'Ruth', 'Howard', 'IT Specialist',80)
,(83, 'Sean', 'Bryant', 'Software Developer',80)
,(84, 'Virginia', 'Foster', 'Engineer',80)
,(85, 'Jonathan', 'Alexander', 'Designer',80)

,(86, 'Deborah', 'Reyes', 'Manager',274)
,(87, 'Catherine', 'Jordan', 'Software Developer',86)
,(88, 'Bruce', 'Morgan', 'Engineer',86)
,(89, 'Alice', 'Cooper', 'Designer',86)
,(90, 'Philip', 'Reed', 'Software Developer',86)
,(91, 'Eugene', 'Butler', 'Engineer',86)

,(92, 'Heather', 'Bennett', 'Manager',274)
,(93, 'Kathy', 'Collins', 'Designer',92)
,(94, 'Diane', 'Hughes', 'IT Specialist',92)
,(95, 'Albert', 'Washington', 'Software Developer',92)
,(96, 'Frances', 'Barnes', 'Engineer',92)
,(97, 'Annie', 'Henderson', 'Designer',92)

,(98, 'Henry', 'Jenkins', 'Manager',1)
,(99, 'Beverly', 'Kennedy', 'Software Developer',98)
,(100, 'Clarence', 'Webb', 'Engineer',98)
,(101, 'Evelyn', 'Russell', 'Designer',98)
,(102, 'Gerald', 'Coleman', 'Software Developer',98)
,(103, 'Dorothy', 'Foster', 'Engineer',98)
;
-- ... continue in this pattern
