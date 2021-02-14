FROM openjdk:8
WORKDIR /app
ADD target/voter-app-0.0.1-SNAPSHOT.jar .
EXPOSE ${SERVER_PORT}
ENTRYPOINT ["java", "-jar", "voter-app-0.0.1-SNAPSHOT.jar"]
