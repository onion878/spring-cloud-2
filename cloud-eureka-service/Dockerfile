FROM java:8
ADD target/cloud-eureka-service.jar cloud-eureka-service.jar
EXPOSE 9001
LABEL name=cloud-eureka-service
ENTRYPOINT ["java", "-jar", "cloud-eureka-service.jar"]