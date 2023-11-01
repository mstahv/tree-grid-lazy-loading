# Lazy Loading Hierarchical Data with Vaadin and Spring Boot

This project is a simple example of how to lazy load data in the UI from a relational database. Currently, the example uses TreeTable from Viritin ad-on as the official TreeGrid component currently has limited support for lazy loading of hierarchical data.

The default branch uses PostgreSQL and a trivial table structure for easy and efficient maintenance of the data. The query then needs to use standard, but rather complex Common Table Expressions (CTE).

In this branch we use non-standard `CONNECT BY` query available in Oracle. The query is not as flexible as CTE queries (also supported by Oracle), but far easier to understand and maintain. The CONNECT BY query is also available in IBM DB2.

## Running the Application

The app is a demo app so you should check it out to your IDE and run from there. You need to have Docker installed and running as the demo uses TestContainers to start up the DB server (and wires that to the Spring Boot JDBC). **Use the main method from LocalDevApplication.java to start the app**.

In case you happen to be developing on M1 Mac, not that the Oracle XE image is not available for ARM architecture. Check [these instructions](https://blog.jdriven.com/2022/07/running-oracle-xe-with-testcontainers-on-apple-silicon/) for a workaround. 