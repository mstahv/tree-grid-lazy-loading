# Lazy Loading Hierarchical Data with Vaadin and Spring Boot

This project is a simple example of how to lazy load data in the UI from a relational database. Currently, the example uses TreeTable from Viritin ad-on as the official TreeGrid component currently has limited support for lazy loading of hierarchical data.

The default branch uses PostgreSQL and a trivial table structure for easy and efficient maintenance of the data. The query then needs to use rather complex Common Table Expression (CTE) and the query may end up being inefficient with large datasets. Couple of variations for different RDBMS techniques and a related article about the topic is under construction...

## The RDBMS variation in this branch: Using the ltree column type in PostgreSQL

PostgreSQL has a handy ltree column type that can be used to store hierarchical data. The column type is not available in the default installation so you need to install the extension first. The data is then in format 'ROOT.LEVEL1.LEVEL2...' and there are numerous functions and operators to query the data. The query is then rather simple and efficient, but the data maintenance is a bit more complex.

In this example I used the ltree as the primary key, containing the whole path:
```sql
managerPath ltree PRIMARY KEY
```

The table structure and queries is this way rather simple.

For a bit more flexibility and simplicity when maintaining the data, one could combine the managerPath with similar employeeId and managerId approach as in the main branch, and maintain the ltree column with triggers. This way the data maintenance would be as simple as in the main branch, but the queries would be as simple as here as one can utilize the ltree column describing the parent path. This approach is described in [an article by Cris Farmiloe](https://coderwall.com/p/whf3-a/hierarchical-data-in-postgres).


## Running the Application

The app is a demo app so you should check it out to your IDE and run from there. You need to have Docker installed and running as the demo uses TestContainers to start up the DB server (and wires that to the Spring Boot JDBC). **Use the main method from LocalDevApplication.java to start the app**.
