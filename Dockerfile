# ---- Build stage ----
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copy Maven wrapper + pom first (better caching)
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN ./mvnw -q -DskipTests dependency:go-offline

# Copy source and build
COPY src/ src/
RUN ./mvnw -q clean package -DskipTests

# ---- Run stage ----
FROM eclipse-temurin:17-jre
WORKDIR /app

# Copy the jar from build stage
COPY --from=build /app/target/*.jar app.jar

# Render sets PORT env var; Spring should listen on it
ENV PORT=8080
EXPOSE 8080

CMD ["sh", "-c", "java -Dserver.port=$PORT -jar app.jar"]
