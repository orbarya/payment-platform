# Stage 1: Build the application
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Set working directory inside the image (not affecting local filesystem)
WORKDIR /app

# Copy files from the build context, which is set to the root of the project on the host machine
COPY . .

# Change to the specific microservice directory for building
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM openjdk:17-bullseye
RUN apt-get update && apt-get install -y net-tools nano telnet
# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY --from=build /app/payment-service/target/*.jar payment-service.jar

# Expose the ports your application uses
EXPOSE 8080/tcp 5510/tcp

# Run the jar file
CMD ["java", "-jar", "/app/payment-service.jar"]