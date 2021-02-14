FROM openjdk:8
WORKDIR /app
ADD target/survey-app-0.0.1-SNAPSHOT.jar .
EXPOSE ${SERVER_PORT}
ENTRYPOINT ["java", "-jar", "survey-app-0.0.1-SNAPSHOT.jar"]
