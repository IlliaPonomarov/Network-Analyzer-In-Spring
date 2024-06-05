FROM eclipse-temurin:17-jdk-alpine


RUN apk update
ARG JAR_FILE

COPY ${JAR_FILE} app.jar
EXPOSE 6565
ENTRYPOINT ["java", "-jar", "app.jar"]


