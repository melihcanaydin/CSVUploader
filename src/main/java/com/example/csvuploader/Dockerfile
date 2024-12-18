FROM openjdk:17-jdk-slim

WORKDIR /app

COPY gradle /app/gradle
COPY gradlew /app/
COPY build.gradle /app/
COPY settings.gradle /app/
COPY src /app/src

RUN chmod +x gradlew

RUN ./gradlew clean build --no-daemon

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "build/libs/CSVUploader-0.0.1-SNAPSHOT.jar"]