# 1. ETAPA DE CONSTRUCCIÓN (BUILD STAGE)
FROM maven:3.9.5-amazoncorretto-21 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src

# PASO CRÍTICO: Compilamos y generamos el JAR
# El archivo JAR se llamará App-0.0.1-SNAPSHOT.jar 
# (Basado en <artifactId>App</artifactId>)
RUN mvn clean package -DskipTests

# ----------------------------------------------------
# 2. ETAPA FINAL (RUNTIME STAGE)
FROM amazoncorretto:21-alpine-jdk

# Define el nombre del JAR basándote en tu pom.xml
# <artifactId>-<version>.jar
ARG JAR_FILE=target/App-0.0.1-SNAPSHOT.jar

# COPIA CORREGIDA: Buscamos y copiamos el archivo generado en la etapa de build
# Si tu proyecto se llama ScoreSense-App, aquí sería ScoreSense-App-0.0.1-SNAPSHOT.jar. 
# Si el nombre es App, lo dejamos así.
COPY --from=build /app/${JAR_FILE} app.jar

# Exponemos el puerto
EXPOSE 8081

# ENTRYPOINT para la ejecución
ENTRYPOINT ["java", "-jar", "app.jar"]