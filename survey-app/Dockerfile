FROM openjdk:8
WORKDIR /app
ADD ./target/ ./survey-app.jar
EXPOSE ${SERVER_PORT}
ENTRYPOINT ["java","-jar","survey-app.jar"]
