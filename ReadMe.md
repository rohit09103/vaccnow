VaccNow

Requirements to build and run:
1) Java 1.8 or higher
2) maven installed and configured.

Steps to build and run:
1) mvn clean install -DskipTests #  building he fat jar and skipping tests
2) mvn clean verify # to execute tests only
3) java -jar vaccnow-0.0.1-SNAPSHOT.jar # run from command line.
4) In IDE run SpringBootApplicationVaccNow.java as java application.

Postman collection can be found in root directory to import and execute rest api's.

Default port is 8080

DB used is H2-in-memory so no external DB required.
H2 console can be access at http://localhost:8080/h2-console
Details required to access h2 console:
DB url: jdbc:h2:file:~/test
DB driverClassName: org.h2.Driver
DB username: temp
Db password: 		#leave blank

SQL DDL and DML statements can be found in src/main/resources.

Tables created and populated on application startup and refreshed on each startup.

