FROM maven:3.6.3-jdk-11 as builder
WORKDIR /app
COPY . .
RUN mvn install -DskipTests


FROM openjdk:11-jre-slim
LABEL maintainer="Martin Besozzi <embesozzi@gmail.com>"
ARG GIT_SHA1=unspecified
LABEL git_commit=$GIT_SHA1
ARG BUILD_DATE=unspecified
LABEL build_date=$BUILD_DATE

WORKDIR /app
COPY --from=builder /app/target/*.jar ./app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","./app.jar"]
