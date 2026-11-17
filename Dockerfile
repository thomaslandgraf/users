FROM gradle:9-jdk25 AS build

WORKDIR /app

COPY . .

RUN gradle bootJar --no-daemon

FROM eclipse-temurin:25-jre

WORKDIR /app

COPY --from=build /app/build/libs/*.jar /app/users.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/users.jar"]