FROM gradle:8.2.1-jdk17 as build

WORKDIR /app

COPY src src
COPY build.gradle build.gradle
COPY gradle.properties gradle.properties
COPY settings.gradle settings.gradle

RUN gradle clean build --info

FROM gcr.io/distroless/java17:nonroot

WORKDIR /app

COPY --from=build /app/build/libs/*.jar connection-service.jar

CMD ["connection-service.jar"]

