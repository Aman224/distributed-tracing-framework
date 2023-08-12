FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY target/tracing-demo-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar tracing-demo-0.0.1-SNAPSHOT.jar