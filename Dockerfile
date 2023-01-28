FROM eclipse-temurin:17

LABEL mentainer="javaguides.net@gmail.com"

WORKDIR /app

COPY target/Spring-Boot-Blog-0.0.1-SNAPSHOT.jar /app/springboot-restful-webservices.jar

ENTRYPOINT ["java", "-jar", "springboot-restful-webservices.jar"]