# ---- Stage 1: Build the JAR ----
FROM eclipse-temurin:17-jdk-alpine AS build

WORKDIR /app

# Copy project files
COPY . .

# Ensure maven wrapper is executable and fix line endings if necessary
RUN chmod +x mvnw

# Run the build
RUN ./mvnw clean package -DskipTests

# ---- Stage 2: Run the JAR ----
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
