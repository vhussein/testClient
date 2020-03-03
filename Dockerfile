#FROM frolvlad/alpine-oraclejdk8:slim
#VOLUME /tmp
#ADD spring-petclinic-rest-owner-1.7.jar app.jar
#RUN sh -c 'touch /app.jar'
#ENV JAVA_OPTS=""
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]

FROM openjdk:8-jre-alpine
ADD target/testClient-1.0-SNAPSHOT.jar app.jar
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=test", "/app.jar"]


#CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=test", "/app.jar"]


