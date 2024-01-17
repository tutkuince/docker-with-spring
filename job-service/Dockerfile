FROM maven:3.8.6-eclipse-temurin-17 AS BUILDER

WORKDIR build

COPY src src
COPY pom.xml pom.xml
RUN mvn clean package

# -------------------

FROM bellsoft/liberica-openjdk-alpine:17.0.3

LABEL authors="tutku"

WORKDIR /usr/share/app

#COPY target/*.jar app.jar
COPY --from=BUILDER /build/target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]