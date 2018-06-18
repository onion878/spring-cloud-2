FROM java:8
ADD target/cloud-server.jar cloud-server.jar
EXPOSE 9003
LABEL name=cloud-server
ENTRYPOINT ["java", "-jar", "cloud-server.jar"]