# VaccNow

As a cautious action after Covid-19, VaccNow is an healthcare organization managing the process of Covid-19 vaccine to public,
so that the VaccNow is planning to build multi their digital channels for consuming a modern API for basic features. This is
planned to be API first approach, of well-tested functions and enabling agility of later modifications.

## Table of contents
  * Technologies
  * Setup
  * APIs
  * ER Diagram

## Technologies
  * Java: 8
  * springboot: 2.1.5
  * H2 DB
  * Junit 5
  * Swagger

## Setup
  * Import as maven project into any IDE.
  * Build the project with maven command 'mvn clean install'.
  * Run the application:
  * Either from commandLine with 'java -jar "path to jar file"'
  * Or directly run 'SpringBootApplicationvaccNow' as java application in your IDE.
  * App will run on port 8080.
  * Swagger is available @ http://localhost:8080/swagger-ui.html
  * H2 DB console details:
  * Link: http://localhost:8080/h2-consol/
  
## API
  * Postman collection is available @ ![Postman Collection]https://github.com/rohit09103/vaccnow/blob/master/Vacc.postman_collection.json)
  
## ER Diagram
  * ![ERD](https://github.com/rohit09103/vaccnow/blob/master/ERD.PNG)
  
## DB Details
  * DB url: jdbc:h2:file:~/test 
  * DB driverClassName: org.h2.Driver 
  * DB username: temp 
  * DB password: #leave blank
  * DB schema is present @ ![schema.sql](https://github.com/rohit09103/vaccnow/blob/master/src/main/resources/schema.sql)
  * DB seed data is available @ ![data.sql](https://github.com/rohit09103/vaccnow/blob/master/src/main/resources/data.sql)
  * Data is automatically seeded at app startup.
  * ![DB connection](https://github.com/rohit09103/vaccnow/blob/master/h2-console.PNG)
