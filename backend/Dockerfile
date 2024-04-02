FROM openjdk:17
VOLUME /tmp
ARG JAR_FILE1=build/libs/backend-0.0.1-SNAPSHOT-plain.jar
ARG JAR_FILE2=build/libs/backend-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE1} jasumin.jar
COPY ${JAR_FILE2} jasumin.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/jasumin.jar"]