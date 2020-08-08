FROM openjdk:8-jre
RUN mkdir app
ARG JAR_FILE
ADD /target/${JAR_FILE} /app/stoom-0.0.1-SNAPSHOT.jar
WORKDIR /app
ENTRYPOINT java -jar stoom-0.0.1-SNAPSHOT.jar