FROM openjdk:21-jdk
COPY target/org-meta*.jar org-metadata.jar
ENTRYPOINT ["java", "-jar", "/org-metadata.jar"]