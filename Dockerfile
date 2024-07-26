
# Stage 1: Build the application with Maven
FROM maven:latest AS builder
WORKDIR /usr/src/app
COPY . .
RUN mvn clean install

# Stage 2: Create a lightweight image with the JAR file
FROM openjdk:latest
WORKDIR /usr/src/app
COPY --from=builder /usr/src/app/target/FinalProjectOOP-0.0.1-SNAPSHOT.jar ./
EXPOSE 8080 17496
CMD ["java", "-jar", "FinalProjectOOP-0.0.1-SNAPSHOT.jar"]
