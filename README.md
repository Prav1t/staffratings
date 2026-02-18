Staff Ratings Application (Assignment 2)


Live Demo: https://staffratings.onrender.com


Features

Create staff ratings with validation

View all submitted ratings

Persistent PostgreSQL database storage

Server-side validation with error handling

Repository persistence (save, retrieve, delete)

Automated tests (validation + persistence)

Dockerized deployment

Cloud deployment on Render


Tech Stack

Java 17

Spring Boot 3

Spring MVC

Spring Data JPA

PostgreSQL

Thymeleaf

JUnit 5

Docker

Render



How to Run Locally

Prerequisites

Java 17+

PostgreSQL

Maven 

1. Create database
CREATE DATABASE staffratings;

2. Configure DB credentials

Update src/main/resources/application.properties if needed:

spring.datasource.url=jdbc:postgresql://localhost:5432/staffratings
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

3. Run the app
./mvnw spring-boot:run

4. Open in browser
http://localhost:8080

How to Deploy (Render)

This app uses Docker + Render PostgreSQL.

Create Render PostgreSQL

Render Dashboard → New → PostgreSQL
Copy:

Internal DB URL

Username

Password

Set environment variables (Render Web Service)
DATABASE_URL=jdbc:postgresql://HOST:PORT/DATABASE
DB_USERNAME=USERNAME
DB_PASSWORD=PASSWORD

4. Deploy

Render → Manual Deploy → Deploy latest commit

5. Open deployed app
https://your-service-name.onrender.com


AI Declaration

used chat gpt on parts of the assignment to help with syntax.



