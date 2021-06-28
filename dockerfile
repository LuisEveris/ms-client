FROM openjdk:8-jdk-alpine
EXPOSE 8002
COPY "target/ms-client-0.0.1-SNAPSHOT.jar" "app.jar"
ENTRYPOINT ["java","-jar","app.jar"]