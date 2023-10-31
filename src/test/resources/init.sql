-- Table and test data derived from MS SQL Server sample
CREATE EXTENSION ltree;
-- Create employees table.
CREATE TABLE employees
(
    managerPath ltree PRIMARY KEY,
    firstName varchar(30)  NOT NULL,
    lastName  varchar(40) NOT NULL,
    title varchar(50) NOT NULL
);
-- Test data
INSERT INTO employees VALUES
 ('1', 'Ken', 'Sánchez', 'Chief Executive Officer')
,('1.273', 'Bria', 'Welcker', 'Vice President of Sales')
,('1.273.274', 'Stephe', 'Jiang', 'North American Sales Manager')
,('1.273.274.275', 'Michael', 'Blythe', 'Sales Representative')
,('1.273.274.276', 'Linda', 'Mitchell', 'Sales Representative')
,('1.273.285', 'Syed', 'Abbas', 'Pacific Sales Manager')
,('1.273.285.286', 'Lyn', 'Tsoflias', 'Sales Representative')
,('1.273.16',  'David','Bradley', 'Marketing Manager')
,('1.273.16.23',  'Mary', 'Gibso', 'Marketing Specialist');
