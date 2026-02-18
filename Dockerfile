FROM maven:3.9.12-eclipse-temurin-25 AS build
COPY . .

RUN mvn clean package -DskipTests

FROM eclipse-temurin:25-jre-alpine
COPY --from=build /target/asg1-0.0.1-SNAPSHOT.jar staffratings.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","asg1.jar" ]
