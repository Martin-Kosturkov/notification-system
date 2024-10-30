FROM openjdk:21-jdk
COPY target/notifi*.jar notification-processor.jar
ENTRYPOINT ["java", "-jar", "/notification-processor.jar"]