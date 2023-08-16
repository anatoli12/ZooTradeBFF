# Use the official OpenJDK image as the base image
FROM openjdk:17

# Set the working directory within the container
WORKDIR /app

# Copy the packaged JAR file from the target directory into the container
COPY target/tinqin-bff.jar app.jar
# Expose the port that your application runs on
EXPOSE 8082

# Define the command to run your application
CMD ["java", "-jar", "app.jar"]