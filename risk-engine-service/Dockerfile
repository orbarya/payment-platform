# Stage 1: Build the application
FROM maven:3.9.2-eclipse-temurin-17 AS build

# Set working directory inside the image (not affecting local filesystem)
WORKDIR /app

# Copy files from the build context, which is set to the root of the project on the host machine
COPY . .

# Build the project, including all modules
RUN mvn clean package -DskipTests


FROM openjdk:17-bullseye
RUN apt-get update && apt-get install -y net-tools nano telnet

# Set the working directory in the container
WORKDIR /app

# Copy the packaged executable jar file into the container
COPY --from=build /app/risk-engine-service/target/*.jar risk-engine-service.jar

# Expose the ports the application uses
EXPOSE 8080/tcp 5510/tcp

# Run the jar file
CMD ["java", "-jar", "/app/risk-engine-service.jar"]