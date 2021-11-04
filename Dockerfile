FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY target/dotjoin-0.0.0.jar DotJoin.jar
ENTRYPOINT ["java", "-jar", "DotJoin.jar"]