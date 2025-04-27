FROM openjdk:21-slim

WORKDIR /app

COPY build/libs/*.jar app.jar

EXPOSE 4000

ENTRYPOINT ["java", "-jar", "app.jar"]
