FROM java:8
ADD target/cloud-web.jar cloud-web.jar
EXPOSE 9002
LABEL name=cloud-web
ENTRYPOINT ["java", "-jar", "cloud-web.jar"]