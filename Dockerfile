FROM adoptopenjdk/openjdk11
EXPOSE 8081
ADD /target/UserModule-0.0.1-SNAPSHOT.jar UserModule.jar
ENTRYPOINT ["java","-jar","/UserModule.jar"]