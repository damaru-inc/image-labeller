FROM openjdk:13-jdk-alpine
ARG JAR_NAME

ADD ${JAR_NAME}.jar spring-boot-webapp.jar

ENTRYPOINT exec java -jar /spring-boot-webapp.jar
