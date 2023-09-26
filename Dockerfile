
FROM openjdk:17

COPY target/demopost-0.0.1-SNAPSHOT.jar demoPost.jar

ENTRYPOINT ["java","-jar","demoPost.jar"]