################################
##         BUILD STEP         ##
################################
FROM maven:3.6.3-jdk-8 AS build
WORKDIR /compile/
COPY . .
RUN mvn clean package -DskipTests=true

################################
##         IMAGE STEP         ##
################################
FROM openjdk:8-jdk-alpine
COPY --from=build /compile/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]