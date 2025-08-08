# =========================================================================
# Stage 1: Build the application using Maven
# =========================================================================
FROM eclipse-temurin:17-jdk-jammy as builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper files (if you use them) and the pom.xml file
# By copying pom.xml first, we leverage Docker's layer caching.
# Dependencies are only re-downloaded if pom.xml changes.
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download all the dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the source code
COPY src ./src

# Package the application, skipping the tests for a faster build
RUN ./mvnw package -DskipTests

# =========================================================================
# Stage 2: Create the final, lightweight production image
# =========================================================================
FROM eclipse-temurin:17-jre-jammy

# Set the working directory
WORKDIR /app

# The port your application runs on (default for Spring Boot is 8080)
EXPOSE 8080

# Copy the built JAR file from the 'builder' stage
# The name 'back-end-0.0.1-SNAPSHOT.jar' comes from your pom.xml
COPY --from=builder /app/target/back-end-0.0.1-SNAPSHOT.jar app.jar

# The command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]