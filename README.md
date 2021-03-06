
# Spring Boot Project Starter

### A _ready to use_ web application based on Spring Boot v2.2.4.

This application contains the following:
* Spring Boot actuator, devtools and web starters.
* Lombok dependency and annotation processing.
* Actuator endpoints for info, health and metrics.
* Enabled junit5 and explicit exclusion of junit4.
* Oracle database driver dependencies
* Inclusion of GIT properties in actuator _info_ endpoint

# Getting Started
1. clone this repo
2. open a command window and navigate to `spring-boot-project-starter` directory
3. build and run the application: `./gradlew bootRun`
4. verify the application is running using `http://localhost:8080/actuator/health`
5. Swagger UI should be displayed at `http://localhost:8080`

## Deploy to Cloud Foundry
1. open a command window and navigate to `spring-boot-project-starter` directory
2. create the deploy artifact using `./gradlew clean bootJar`
3. verify `build/libs/spring-boot-project-starter.jar` exists
4. deploy to PCF using `cf push`
