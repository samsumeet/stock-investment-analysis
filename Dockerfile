FROM adoptopenjdk/openjdk11:latest
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} stock-analysis-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/stock-analysis-0.0.1-SNAPSHOT.jar"]