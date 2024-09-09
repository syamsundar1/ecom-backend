FROM openjdk:17
EXPOSE 8080
ADD target/ecom-backend.jar ecom-backend.jar
ENTRYPOINT ["java","-jar","/ecom-backend.jar"]