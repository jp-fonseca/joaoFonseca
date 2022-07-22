FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} joaofonseca.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","joaofonseca.jar"]
