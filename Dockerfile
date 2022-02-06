FROM openjdk:17-slim-buster

COPY build/libs/MsPermission-0.0.1-SNAPSHOT.jar .

ENTRYPOINT java -jar MsPermission-0.0.1-SNAPSHOT.jar