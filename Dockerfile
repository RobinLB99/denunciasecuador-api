# Usar una imagen base con JDK 21 y Maven
FROM maven:3-eclipse-temurin-21-noble

# Establecer el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar solo el pom.xml para aprovechar el cache de capas de Docker
# Si el pom.xml no cambia, las dependencias no se volverán a descargar
COPY pom.xml .

# Descargar todas las dependencias del proyecto
RUN mvn dependency:go-offline

# Copiar todo el código fuente de tu proyecto al contenedor
COPY src ./src

# Exponer el puerto 8080 para que la aplicación sea accesible
EXPOSE 8080

# Comando para arrancar la aplicación en modo de desarrollo con Maven
# Esto permite el "hot reload" si tienes spring-boot-devtools
CMD ["mvn", "spring-boot:run"]
