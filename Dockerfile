# 1. ETAPA DE CONSTRUCCIÓN (BUILD STAGE)
# Usamos una imagen base de Maven para construir el proyecto.
FROM maven:3.9.5-amazoncorretto-21 AS build

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el archivo pom.xml para descargar las dependencias primero
COPY pom.xml .

# Descargamos las dependencias. Esto se cachea si el pom.xml no cambia.
RUN mvn dependency:go-offline

# Copiamos el resto de los archivos del proyecto (código fuente)
COPY src ./src

# Empaquetamos la aplicación en un archivo JAR
RUN mvn clean package -DskipTests

# ----------------------------------------------------
# 2. ETAPA FINAL (RUNTIME STAGE)
# Usamos una imagen más ligera (solo el JRE) para el runtime,
# lo cual es más seguro y reduce el tamaño de la imagen final.
FROM amazoncorretto:21-alpine-jdk

# Define un argumento para el nombre del archivo JAR (debe coincidir con tu proyecto)
ARG JAR_FILE=target/App-0.0.1-SNAPSHOT.jar

# Copiamos el archivo JAR compilado desde la etapa de construcción
# Asegúrate de que el nombre del JAR sea correcto
COPY --from=build /app/${JAR_FILE} app.jar

# Exponemos el puerto en el que la aplicación escucha (Render lo ignora, pero es buena práctica)
EXPOSE 8081

# Comando de entrada para ejecutar la aplicación Spring Boot
# Utilizamos la variable de entorno $PORT (proporcionada por Render) si está disponible,
# y si no, usa el puerto 8081.
ENTRYPOINT ["java", "-jar", "app.jar"]