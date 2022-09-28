FROM openjdk:11-jre
WORKDIR /app
COPY ./target/*.jar app.jar
ENTRYPOINT ["java","-jar","./app.jar"]