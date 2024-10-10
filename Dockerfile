# Use an official Amazon Corretto runtime as a parent image
FROM amazoncorretto:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY target/*.jar app.jar

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file with the active profile set to prod
ENTRYPOINT ["java", "-jar", "/app/app.jar", "--spring.profiles.active=prod"]