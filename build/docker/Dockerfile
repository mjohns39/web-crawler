#Specify the jdk.  It is standard in docker to use the open jdk.  See:  https://hub.docker.com/_/openjdk/
FROM openjdk:11

VOLUME /buildit-web-crawler-volume

ARG JAR_FILE
COPY ${JAR_FILE} app.jar


EXPOSE 8080

CMD ["java", "-jar", "/app.jar", "-Xmx128m"]