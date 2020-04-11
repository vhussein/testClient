#FROM frolvlad/alpine-oraclejdk8:slim
#VOLUME /tmp
#ADD spring-petclinic-rest-owner-1.7.jar app.jar
#RUN sh -c 'touch /app.jar'
#ENV JAVA_OPTS=""
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

FROM openjdk:8-jre-alpine
ADD target/test-client.jar app.jar
#CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=dev", "/app.jar"]
ENTRYPOINT exec java $JAVA_OPTS $DEBUG_OPTS $SPRING_PROFILE -jar /app.jar
