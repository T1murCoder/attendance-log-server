FROM maven:3.8.7-eclipse-temurin-19 as builder
WORKDIR /app
COPY . /app/.
RUN mvn -f /app/pom.xml clean package spring-boot:repackage -Dmaven.test.skip

FROM eclipse-temurin:19-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/*.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "/app/*.jar"]