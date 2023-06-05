
FROM maven:3.8-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app/
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:17.0.2 
COPY --from=build /home/app/target/FinalProject*.jar /usr/local/lib/FinalProject.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/FinalProject.jar"]