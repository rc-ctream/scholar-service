#############################################
# 1) Build-Stage: Maven + Java 21
#############################################
FROM maven:3.9.8-eclipse-temurin-21 AS build

WORKDIR /build

# POM kopieren und alle Abhängigkeiten offline auflösen
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Quellcode kopieren und Projekt bauen (inkl. Tests)
COPY src/ src/
RUN mvn clean package -B

#############################################
# 2) Runtime-Stage: schlankes Alpine-JRE 21
#############################################
FROM eclipse-temurin:21-jre-alpine AS runtime

# Security: Nicht-root-User anlegen
RUN addgroup -S app && adduser -S app -G app
USER app:app

WORKDIR /app

# Nur das fertige JAR übernehmen
COPY --from=build /build/target/*.jar scholar-service.jar

EXPOSE 8080

# Entropie-Flag für schnelleren Startup
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "scholar-service.jar"]