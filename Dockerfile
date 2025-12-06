# 1. ETAPA DE CONSTRUCCIÓN (BUILD STAGE)
FROM maven:3.9.5-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src

RUN mvn clean package -DskipTests

# ----------------------------------------------------
FROM amazoncorretto:21-alpine-jdk


ARG JAR_FILE=target/App-0.0.1-SNAPSHOT.jar

COPY --from=build /app/${JAR_FILE} app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]