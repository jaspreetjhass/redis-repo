FROM openjdk:8
WORKDIR /app
ADD ./target/ ./voter-app.jar
EXPOSE ${SERVER_PORT}
ENTRYPOINT ["java","-jar","voter-app.jar"]
