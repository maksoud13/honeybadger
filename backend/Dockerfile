FROM maven:3.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn dependency:resolve dependency:resolve-plugins

COPY src ./src

RUN mvn clean package -DskipTests -q

FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/honeybodger-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
