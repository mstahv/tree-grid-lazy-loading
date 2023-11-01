# Lazy Loading Hierarchical Data with Vaadin and Spring Boot

This project is a simple example of how to lazy load data in the UI from a relational database. Currently, the example uses TreeTable from Viritin ad-on as the official TreeGrid component currently has limited support for lazy loading of hierarchical data.

The default branch uses PostgreSQL and a trivial table structure for easy and efficient maintenance of the data. The query then needs to use standard, but rather complex Common Table Expression (CTE) and the query may end up being inefficient with large datasets.

There are two other branches with alternative strategies:

 * [ltree-postgres](https://github.com/mstahv/tree-grid-lazy-loading/tree/ltree-postgres) - uses PostgreSQL `ltree` extension to store the hierarchy in a single column. This is a bit more complex to maintain/mutate, but the query is much simpler.
 * [oracle-connect-by](https://github.com/mstahv/tree-grid-lazy-loading/tree/oracle-connnect-by) - uses Oracle's CONNECT BY syntax to query the data. The table and data maintenance is as easy as in the main branch, but the queries are easier to understand. The CONNECT BY syntax is also available for IBM DB2.

## Running the Application

The app is a demo app so you should check it out to your IDE and run from there. You need to have Docker installed and running as the demo uses TestContainers to start up the DB server (and wires that to the Spring Boot JDBC). **Use the main method from LocalDevApplication.java to start the app**.
