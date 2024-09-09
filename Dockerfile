FROM openjdk:17-jdk
WORKDIR /app
COPY target/ecom-backend.jar /app/ecom-backend.jar
EXPOSE 8080
CMD ["java","-jar","/ecom-backend.jar"]