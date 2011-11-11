RhoconnectJavaDemo
===

Sample of complete Java back-end application using Spring 3.0 MVC as front end technology and Hibernate 
as back-end ORM technology. For this application Maven2 is also used for build and dependency management and 
embedded in-memory database (HSQL) to persist the data.
 
This sample is based on excellent tutorial from "Spring 3.0 MVC Series": 
http://viralpatel.net/blogs/2010/11/spring3-mvc-hibernate-maven-tutorial-eclipse-example.html

Prerequisites
 * Java (1.6), Maven2 (2.2.1), Git
 * Eclipse Java EE IDE for Web Developers.

The application uses an embedded in-memory database (HSQL) that is setup by the Spring spring-servlet.xml class. 
See files schema.sql and test-data.sql in src/main/resources for the schema created during the application's 
startup and the initial records inserted into the table's database. 
Using the in-memory database means there is no need to setup and run a separate database server for this application.

You can run the application using Maven.
In a terminal of the project's root folder issue these commands:

	:::term
    mvn clean
    mvn jetty:run

When you see [INFO] Started Jetty Server open a web browser and navigate to URL
http://localhost:8080/contacts/index 

Yet another option is to create executable WAR file

	:::term
    mvn war:war

and deploy it into the Tomcat server.   
