FROM eclipse-temurin:17-jdk-alpine
WORKDIR /ntparser
RUN apk update
COPY . .

EXPOSE 6565
ENTRYPOINT ["java", "-jar", "build/libs/Network-Analyzer-Spring-Boot-0.0.1-SNAPSHOT.jar"]


