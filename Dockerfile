FROM gradle:4.7.0-jdk8-alpine as builder

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build --no-daemon 

FROM openjdk:8-jre-slim as finalApp

RUN mkdir /app

COPY --from=builder /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar

EXPOSE 8080

ENTRYPOINT ["java","-Dspring.profiles.active=container","-jar","/app/spring-boot-application.jar"]
