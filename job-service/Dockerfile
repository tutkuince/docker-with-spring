FROM bellsoft/liberica-openjdk-alpine:17.0.3

LABEL authors="tutku"

WORKDIR /usr/share/app

COPY target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]