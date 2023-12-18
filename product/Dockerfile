FROM openjdk:21-jdk

ARG JAR_FILE=*.jar
COPY ${JAR_FILE} product-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "product-0.0.1-SNAPSHOT.jar"]