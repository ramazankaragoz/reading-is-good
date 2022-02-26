FROM openjdk:11
ADD ./target/reading-is-good-0.0.1-SNAPSHOT.jar app.jar
WORKDIR usr/src
ENTRYPOINT ["java","-jar", "/app.jar"]
EXPOSE 8080