FROM maven:3.5.4-jdk-8-alpine as builder

RUN mkdir -p /build
RUN mkdir -p /build/logs

WORKDIR /build
COPY pom.xml /build
RUN mvn dependency:resolve && mvn compile