#FROM docker-registry.tools.expedia.com/stratus/primer-base-springboot:8-2.1
#FROM openjdk:8-jdk-alpine
FROM frolvlad/alpine-oraclejdk8:slim
VOLUME /tmp
ADD target/my-pay.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
