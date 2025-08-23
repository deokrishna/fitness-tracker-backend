# Use a lightweight JDK base image
FROM eclipse-temurin:17-jdk-alpine

# Set working directory inside the container
WORKDIR /app

# Copy Maven/Gradle wrapper and project files
COPY . .

# Build the application (if using Maven)
RUN ./mvnw clean package -DskipTests

# Expose the default Spring Boot port
EXPOSE 8080

# Run the Spring Boot JAR
ENTRYPOINT ["java", "-jar", "target/fitness-tracker-0.0.1-SNAPSHOT.jar"]
