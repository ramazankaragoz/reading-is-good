# reading-is-good
ReadingIsGood is an online books retail firm which operates only on the Internet. Main target of ReadingIsGood is to deliver books from its one centralized warehouse to their customers within the same day. That is why stock consistency is the first priority for their vision operations.

# Used technologies
-Java 11
-Spring Boot
-Postgresql
-Docker
-Liquibase for migration and init data
-Junit 5
-Spring Doc Open Api Ui
-Lombok
-Spring security jdbc authentication
-Audit Log is used for entity log

email: user@readingisgood.com password: "pass" for authentication.

To start the application you have to do maven install and run docker-compose.yml.Since the integration test is developed, you can skip the test in maven.
mvn install -Dmaven.test.skip=true
I may add testcontainers to fix this in the future.


![image](https://user-images.githubusercontent.com/21217596/156067989-2af77a74-ede4-446c-9f46-1fa4eb168a34.png)


I made the explanation about using rest services on swagger.so I didn't feel the need to prepare postman requests.Using swagger is simple.

what if it happens if 2 or more users tries to buy one last book
at the same time?

-I solved this problem with version in stock table.I handled optimisticlockexception.

