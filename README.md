# Lazy Loading Hierarchical Data with Vaadin and Spring Boot

This project is a simple example of how to lazy load data in the UI from a relational database. Currently, the example uses TreeTable from Viritin ad-on as the official TreeGrid component currently has limited support for lazy loading of hierarchical data.

The default branch uses PostgreSQL and a trivial table structure for easy and efficient maintenance of the data. The query then needs to use rather complex Common Table Expression (CTE) and the query may end up being inefficient with large datasets. Couple of variations for different RDBMS techniques and a related article about the topic is under construction...

## Running the Application

The app is a demo app so you should check it out to your IDE and run from there. You need to have Docker installed and running as the demo uses TestContainers to start up the DB server (and wires that to the Spring Boot JDBC). **Use the main method from LocalDevApplication.java to start the app**.
