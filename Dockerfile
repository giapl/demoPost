
FROM openjdk:17
COPY target/demopost-0.0.1-SNAPSHOT.jar demoPost.jar
COPY wait-for-it.sh /home/giap/demoPost/wait-for-it.sh
RUN chmod +x /home/giap/demoPost/wait-for-it.sh
ENTRYPOINT ["/home/giap/demoPost/wait-for-it.sh", "database_mysql:3306", "--timeout=10", "--", "java", "-jar", "demoPost.jar"]