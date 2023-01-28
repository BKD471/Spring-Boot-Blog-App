FROM eclipse-temurin:17

LABEL mentainer="bhaskarkumardas9@gmail.com"

WORKDIR /app

COPY target/Spring-Boot-Blog-0.0.1-SNAPSHOT.jar /app/springbootrest-restful-webservices.jar

ENTRYPOINT ["java", "-jar", "springbootrest-restful-webservices.jar"]