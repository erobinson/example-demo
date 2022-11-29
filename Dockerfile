# Build stage
FROM maven:3.6.0-jdk-11-slim AS builder
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn /home/app/pom.xml clean install

# Run stage
FROM openjdk:11-jre-slim
COPY --from=builder /home/app/target/example-demo-*.jar /usr/local/lib/example-demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/example-demo.jar"]